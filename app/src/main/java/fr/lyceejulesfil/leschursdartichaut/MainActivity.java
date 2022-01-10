package fr.lyceejulesfil.leschursdartichaut;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import fr.lyceejulesfil.leschursdartichaut.databinding.ActivityMainBinding;
import fr.lyceejulesfil.leschursdartichaut.ui.googlemaps.GoogleMapsFragment;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    public boolean QSN = false;
    public boolean LN = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Qui Sommes nous ?
        LinearLayout Presentation = (LinearLayout)binding.getRoot().findViewById(R.id.Presentaion);
        LinearLayout LesNouveauter = (LinearLayout)binding.getRoot().findViewById(R.id.Nouveauter);
        Button BtnPresentation = (Button)binding.getRoot().findViewById(R.id.BtnPresentation);
        Button BtnNouveauter = (Button)binding.getRoot().findViewById(R.id.BtnLeNouveauter);

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] mail = new String[]{"danslesyeuxdeloli@gmail.com"};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, mail);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contact Les choeurs d'artichaut");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Envoyé via L'application 'Les choeurs d'artichaut'.");
                startActivity(Intent.createChooser(emailIntent, "Choisissez de l'application"));
            }
        });

        binding.appBarMain.NumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:0624712807"));
                startActivity(dialIntent);
            }
        });


        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        BtnPresentation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(QSN == false)
                {
                    QSN = true;
                    Presentation.setVisibility(View.VISIBLE);
                }
                else
                {
                    QSN = false;
                    Presentation.setVisibility(View.GONE);
                }
            }
        });

        BtnNouveauter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LN == false)
                {
                    LN = true;
                    LesNouveauter.setVisibility(View.VISIBLE);
                }
                else
                {
                    LN = false;
                    LesNouveauter.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_auteur:
                Toast.makeText(this, "Maxime66410 | FurranyStudio", Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_version:
                Toast.makeText(this, "Version : 1.0", Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}