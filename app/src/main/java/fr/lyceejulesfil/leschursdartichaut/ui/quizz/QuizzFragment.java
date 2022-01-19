package fr.lyceejulesfil.leschursdartichaut.ui.quizz;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import fr.lyceejulesfil.leschursdartichaut.MainActivity;
import fr.lyceejulesfil.leschursdartichaut.R;
import fr.lyceejulesfil.leschursdartichaut.databinding.FragmentQuizzBinding;

public class QuizzFragment extends Fragment implements View.OnClickListener {
        // mettre en place des choses
        private Button falseButton;
        private Button trueButton;
        private ImageButton nextButton;
        private ImageButton prevButton;
        private ImageView Image;
        private TextView questionTextView;
        private int correct = 0;

        // pour garder la trace des questions en cours
        private int currentQuestionIndex = 0;

        private questionQuizz[] questionBank = new questionQuizz[] {
                // tableau d'objets de classe Question fournissant des questions à partir de la ressource de chaîne et le bon ans
                new questionQuizz(R.string.Question1, true),
                new questionQuizz(R.string.Question2, true),
                new questionQuizz(R.string.Question3, false),
                new questionQuizz(R.string.Question4, true),
                new questionQuizz(R.string.Question5, true),

        };

    private QuizzViewModel quizzViewModel;
    private FragmentQuizzBinding binding;

    // mise en place de l'affichage
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        quizzViewModel = new ViewModelProvider(this).get(QuizzViewModel.class);

        binding = FragmentQuizzBinding.inflate(inflater, container, false);
        View root = binding.getRoot(); // Récupère tout les objet sur le xml

        // Récupère le bouton Faux
        falseButton = binding.getRoot().findViewById(R.id.false_button);
        // Récupère le bouton Vrai
        trueButton = binding.getRoot().findViewById(R.id.true_button);
        // Récupère le bouton Suivant
        nextButton = binding.getRoot().findViewById(R.id.next_button);
        // Récupère le bouton Précédant
        prevButton = binding.getRoot().findViewById(R.id.prev_button);

        // Récupère le texte de la question
        questionTextView = binding.getRoot().findViewById(R.id.answer_text_view);
        // Récupère l'image de la question
        Image = binding.getRoot().findViewById(R.id.QuizzImage);
        // Si on clique sur bouton Faux
        falseButton.setOnClickListener(this);
        // Si on clique sur bouton Vrai
        trueButton.setOnClickListener(this);
        // Si on clique sur bouton Suivant
        nextButton.setOnClickListener(this);
        // Si on clique sur bouton Précédant
        prevButton.setOnClickListener(this);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //  Vérification des conditions
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.false_button: // Si la on clique sur le bouton faux
                checkAnswer(false);
                break;

            case R.id.true_button:  // Si la on clique sur le bouton Vrai
                checkAnswer(true);
                break;

            case R.id.next_button:  // Si la on clique sur le bouton Suivant

                if (currentQuestionIndex < 5) { // Si la question actuelle n'est pas supérieur à 5
                    currentQuestionIndex = currentQuestionIndex + 1;

                    if (currentQuestionIndex == 5) {    // Affichage du résultat
                        questionTextView.setText(getString(
                                R.string.correct, correct));
                        nextButton.setVisibility(
                                View.INVISIBLE);
                        prevButton.setVisibility(
                                View.INVISIBLE);
                        trueButton.setVisibility(
                                View.INVISIBLE);
                        falseButton.setVisibility(
                                View.INVISIBLE);
                        if (correct > 3) // Si inférieur a 3 question

                            questionTextView.setText(
                                    "Réponse correcte " + correct
                                            + " "
                                            + "sur 5");
                        else
                            Image.setImageResource(
                                    R.drawable.picture02);
                    }
                    else { // Si non on passe a la question suivante
                        updateQuestion();
                    }
                }

                break;
            case R.id.prev_button: // Si on reviens en arrière
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex = (currentQuestionIndex - 1) % questionBank.length;
                    updateQuestion();
                }
        }
    }

    // Mettre a jour la question
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void updateQuestion()
    {
        questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResId());

        // Choix de l'image pour chaque question

        switch (currentQuestionIndex) {
            case 1:
                Image.setImageResource(R.drawable.imagepost01);
                break;
            case 2:
                Image.setImageResource(R.drawable.logobasic);
                break;
            case 3:
                Image.setImageResource(R.drawable.picture05);
                break;
            case 4:
                Image.setImageResource(R.drawable.picture03);
                break;
            case 5:
                Image.setImageResource(R.drawable.fondback2015);
                break;
        }
    }

    // Regarder si la question est Vrai ou Fausse
    private void checkAnswer(boolean userChooseCorrect)
    {
        boolean answerIsTrue = questionBank[currentQuestionIndex].isAnswerTrue();

        int toastMessageId;


        if (userChooseCorrect == answerIsTrue) { // Si elle est vrai alors c'est correct
            toastMessageId = R.string.correct_answer;
            correct++;
        }
        else { // Cas contraire on dit que c'est faux

            toastMessageId = R.string.wrong_answer;
        }

        Toast.makeText(getContext(), toastMessageId, Toast.LENGTH_SHORT).show();
    }
}