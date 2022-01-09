package fr.lyceejulesfil.leschursdartichaut.ui.quizz;

public class questionQuizz {
    // answerResId stockera la question
    private int answerResId;

    // answerTrue stockera la bonne réponse de la question posée
    private boolean answerTrue;

    public questionQuizz(int answerResId, boolean answerTrue)
    {
        // réglage des valeurs par arguments passés dans le constructeur
        this.answerResId = answerResId;
        this.answerTrue = answerTrue;
    }

    // retour de la question passé
    public int getAnswerResId()
    {
        return answerResId;
    }

    // la définition de la question est passée
    public void setAnswerResId(int answerResId)
    {
        this.answerResId = answerResId;
    }

    // retourner la bonne réponse à la question
    public boolean isAnswerTrue()
    {
        return answerTrue;
    }

    // définir la bonne réponse à la question
    public void setAnswerTrue(boolean answerTrue)
    {
        this.answerTrue = answerTrue;
    }
}
