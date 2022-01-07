package fr.lyceejulesfil.leschursdartichaut.ui.quizz;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QuizzViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public QuizzViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Quizz fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}