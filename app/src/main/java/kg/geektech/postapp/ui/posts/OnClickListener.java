package kg.geektech.postapp.ui.posts;

import kg.geektech.postapp.data.models.Post;

public interface OnClickListener {

    void onClick(Post post);

    void onLongClickListener(Post post, int p);
}
