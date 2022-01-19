package fr.lyceejulesfil.leschursdartichaut;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
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

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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

    public boolean QSN = false; // Valeur Booléan qui permet de vérifier si l'utilisateur veux ouvrir a propo
    public boolean LN = false; // Valeur Booléan qui permet de vérifier si l'utilisateur veux ouvrir les nouveautés

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Récupère les LinearLayout et Button
        LinearLayout Presentation = (LinearLayout)binding.getRoot().findViewById(R.id.Presentaion);
        LinearLayout LesNouveauter = (LinearLayout)binding.getRoot().findViewById(R.id.Nouveauter);
        Button BtnPresentation = (Button)binding.getRoot().findViewById(R.id.BtnPresentation);
        Button BtnNouveauter = (Button)binding.getRoot().findViewById(R.id.BtnLeNouveauter);

        // Mise en place de la navbar
        setSupportActionBar(binding.appBarMain.toolbar);

        // Mise en place du bouton poour envoyer un mail
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

        // Mise en place du bouton pour appeler
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
        // Passer chaque ID de menu comme un ensemble d'ID parce que chaque
        // menu doit être considéré comme des destinations de premier niveau.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // Si on clique sur le bouton Presentation ça ouvre et ça ferme le menu
        BtnPresentation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(QSN == false) // Si non visible
                {
                    QSN = true;
                    Presentation.setVisibility(View.VISIBLE); // Mettre visible le texte
                }
                else
                { // Si visible
                    QSN = false;
                    Presentation.setVisibility(View.GONE); // Mettre invisible le texte
                }
            }
        });

        // Si on clique sur le bouton Nouveauter ça ouvre et ça ferme le menu
        BtnNouveauter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LN == false) // Si non visible
                {
                    LN = true;
                    LesNouveauter.setVisibility(View.VISIBLE); // Mettre visible le texte
                }
                else
                { // Si visible
                    LN = false;
                    LesNouveauter.setVisibility(View.GONE); // Mettre invisible le texte
                }
            }
        });

        // Demande permissions dès ouverture
        try {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Créer un option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Gonflez le menu; cela ajoute des éléments à la barre d'action si elle est présente.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // Setup le Support de navigation
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    // Mise en place des configuration des boutons, Menu pour afficher auteur et version
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_auteur:
                Toast.makeText(this, "Maxime66410 | FurranyStudio", Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_version:
                Toast.makeText(this, "Version : 2.0", Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}