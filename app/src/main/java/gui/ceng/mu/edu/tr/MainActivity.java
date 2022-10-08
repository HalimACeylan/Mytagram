package gui.ceng.mu.edu.tr;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import gui.ceng.mu.edu.tr.Post;
import gui.ceng.mu.edu.tr.PostAdapter;

public class MainActivity extends AppCompatActivity {

    List<Post> posts = new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listview);
        PostAdapter adapter = new PostAdapter(this, posts);
        listView.setAdapter(adapter);


        final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>(){


                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode()== Activity.RESULT_OK){
                            Intent data = result.getData();
                            Post post = new Post();
                            assert data != null;
                            post.setMessage(data.getCharSequenceExtra("nsg").toString());
                            post.setImage((Bitmap) data.getParcelableExtra("bitmap"));
                            posts.add(post);
                            ((PostAdapter) listView.getAdapter()).notifyDataSetChanged();

                        }
                    }
                });


        Button btnPost=findViewById(R.id.btnPost);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,PostActivity.class);
                launcher.launch(intent);
            }

        });
    }
}