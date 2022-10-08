package gui.ceng.mu.edu.tr;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PostAdapter extends BaseAdapter {
    public List<Post> posts;
    private LayoutInflater inflater;

    public PostAdapter(Activity activity, List<Post> posts) {
        this.posts = posts;
        this.inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int i) {
        return posts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView;
        rowView = inflater.inflate(R.layout.row,null);
        EditText txtMessage = (EditText) rowView.findViewById(R.id.txtEditView);
        TextView txtLocation = (TextView) rowView.findViewById(R.id.txtview);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imgView);
        Post post = posts.get(i);
        txtMessage.setText(post.getMessage());
        imageView.setImageBitmap(post.getImage());
        if (post.getLocation() != null) {
            txtLocation.setText(post.getLocation().getLatitude() + " "
                    + post.getLocation().getLongitude());
        }
        return rowView;
    }


}
