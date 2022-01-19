package fr.lyceejulesfil.leschursdartichaut.ui.website;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import fr.lyceejulesfil.leschursdartichaut.databinding.FragmentSlideshowBinding;

public class WebsiteFragment extends Fragment {

    private WebsiteViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel = new ViewModelProvider(this).get(WebsiteViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Regarder si on a les permissions

        try {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.INTERNET}, 101);
            }
            else
            {
                WebView web = (WebView) binding.Web;

                WebSettings webSettings = web.getSettings(); // Récupére les settings du webview
                webSettings.setJavaScriptEnabled(true); // Active le JS
                webSettings.setDomStorageEnabled(true); // De définir si le DOM API de stockage est activé
                webSettings.setLoadWithOverviewMode(true); // Charge la WebView complètement dézoomée
                webSettings.setUseWideViewPort(true); // Fait en sorte que la vue Web ait une fenêtre normale
                webSettings.setBuiltInZoomControls(true); // Activer les commandes de zoom et pincer le zoom dans une WebView
                webSettings.setDisplayZoomControls(false); // Désactive les touches de Zooms
                webSettings.setSupportZoom(true); // Active le support du zoom
                webSettings.setDefaultTextEncodingName("utf-8"); // Active l'encodage utf-8

                // Affichage Site Web
                web.loadUrl("https://web.archive.org/web/20130503095723/http://www.leschoeursdartichaut.fr/");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}