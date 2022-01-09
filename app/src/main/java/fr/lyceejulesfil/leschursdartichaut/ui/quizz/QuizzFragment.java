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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        quizzViewModel = new ViewModelProvider(this).get(QuizzViewModel.class);

        binding = FragmentQuizzBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        falseButton = binding.getRoot().findViewById(R.id.false_button);
        trueButton = binding.getRoot().findViewById(R.id.true_button);
        nextButton = binding.getRoot().findViewById(R.id.next_button);
        prevButton = binding.getRoot().findViewById(R.id.prev_button);

        questionTextView = binding.getRoot().findViewById(R.id.answer_text_view);
        Image = binding.getRoot().findViewById(R.id.QuizzImage);
        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.false_button:
                checkAnswer(false);
                break;

            case R.id.true_button:
                checkAnswer(true);
                break;

            case R.id.next_button:

                if (currentQuestionIndex < 5) {
                    currentQuestionIndex
                            = currentQuestionIndex + 1;

                    if (currentQuestionIndex == 5) {
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
                        if (correct > 3)

                            questionTextView.setText(
                                    "Réponse correcte " + correct
                                            + " "
                                            + "sur 5");
                        else
                            Image.setImageResource(
                                    R.drawable.picture02);
                    }
                    else {
                        updateQuestion();
                    }
                }

                break;
            case R.id.prev_button:
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex
                            = (currentQuestionIndex - 1)
                            % questionBank.length;
                    updateQuestion();
                }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void updateQuestion()
    {

        questionTextView.setText(
                questionBank[currentQuestionIndex]
                        .getAnswerResId());

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
    private void checkAnswer(boolean userChooseCorrect)
    {
        boolean answerIsTrue
                = questionBank[currentQuestionIndex]
                .isAnswerTrue();

        int toastMessageId;


        if (userChooseCorrect == answerIsTrue) {
            toastMessageId = R.string.correct_answer;
            correct++;
        }
        else {

            toastMessageId = R.string.wrong_answer;
        }

        Toast.makeText(getContext(), toastMessageId, Toast.LENGTH_SHORT).show();
    }
}