package kg.geektech.postapp.ui.form;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import kg.geektech.postapp.App;
import kg.geektech.postapp.R;
import kg.geektech.postapp.data.models.Post;
import kg.geektech.postapp.databinding.FragmentFormBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormFragment extends Fragment {


    private FragmentFormBinding binding;
    private static final int groupId = 38;
    private static final int userId = 5;
    private Post post;

    public FormFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updatePost();


        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = binding.etTitle.getText().toString();
                String content = binding.etContent.getText().toString();

                if (getArguments() != null) {
                    post.setTitle(title);
                    post.setContent(content);
                    App.api.updatePost(post.getId(), post).enqueue(new Callback<Post>() {
                        @Override
                        public void onResponse(Call<Post> call, Response<Post> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                requireActivity().onBackPressed();
                            }
                        }

                        @Override
                        public void onFailure(Call<Post> call, Throwable t) {
                        }
                    });

                } else {
                    if (!title.equals("") && !content.equals("")) {
                        post = new Post(title, content, userId, groupId);

                        App.api.createPost(post).enqueue(new Callback<Post>() {
                            @Override
                            public void onResponse(Call<Post> call, Response<Post> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    requireActivity().onBackPressed();
                                }
                            }

                            @Override
                            public void onFailure(Call<Post> call, Throwable t) {

                            }
                        });
                    } else {
                        Toast.makeText(requireContext(), "ADD FIELDS", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    void updatePost() {
        if (getArguments() != null) {
            post = (Post) getArguments().getSerializable("post");
            binding.etContent.setText(post.getContent());
            binding.etTitle.setText(post.getTitle());
            binding.btnSend.setText("Update");
        }
    }
}