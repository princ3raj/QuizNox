package com.example.quiznos;

class Question {

    private int mTextResId;
    private boolean mAnswers;

    public Question(int mTextResId, boolean mAnswers) {
        this.mTextResId = mTextResId;
        this.mAnswers = mAnswers;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswers() {
        return mAnswers;
    }

    public void setAnswers(boolean answers) {
        mAnswers = answers;
    }
}

