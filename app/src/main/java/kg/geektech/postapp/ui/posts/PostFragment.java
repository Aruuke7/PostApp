package kg.geektech.postapp.ui.posts;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kg.geektech.postapp.App;
import kg.geektech.postapp.R;
import kg.geektech.postapp.data.models.Post;
import kg.geektech.postapp.data.models.Students;
import kg.geektech.postapp.databinding.FragmentPostBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostFragment extends Fragment implements OnClickListener {

    private FragmentPostBinding binding;
    private NavController navController;
    private PostAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new PostAdapter();
        adapter.setListener(this);
        Students.addAllStudents();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recycler.setAdapter(adapter);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_postFragment_to_formFragment);
            }
        });

        App.api.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setPosts(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });

    }

    @Override
    public void onClick(Post post) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("post", post);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.action_postFragment_to_formFragment, bundle);
    }

    @Override
    public void onLongClickListener(Post post, int p) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Вы хотите удалить пост?")
                .setNegativeButton("Удалить", (dialog, which) -> {
                    App.api.deletePost(post.getId()).enqueue(new Callback<Post>() {
                        @Override
                        public void onResponse(Call<Post> call, Response<Post> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                adapter.removePost(post, p);
                            }
                        }

                        @Override
                        public void onFailure(Call<Post> call, Throwable t) {

                        }
                    });
                })
                .setNeutralButton("Отмена", (dialog, which) -> {
                })
                .show();
    }
}