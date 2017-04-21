package com.example.android.miwok;


public class Word {

    private String mMiwokTranslation;
    private String mDefaultTranslation;
    private int mMiwokImage = NO_IMAGE_PROVIDED;
    private int mMiwokSound;
    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     +     * Create a new Word object.
     +     *
     +     * @param defaultTranslation is the word in a language that the user is already familiar with
     +     *                           (such as English)
     +     * @param miwokTranslation is the word in the Miwok language
     +     */

    public Word (String defaultTranslation, String miwokTranslation, int miwokSound) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mMiwokSound = miwokSound;
    }

    /**
     +     * Create a new Word object.
     +     *
     +     * @param defaultTranslation is the word in a language that the user is already familiar with
     +     *                           (such as English)
     +     * @param miwokTranslation is the word in the Miwok language
     +     *
     +     * @param miwokImage is the resource ID for the image associated with the word
     +     */

    public Word (String defaultTranslation, String miwokTranslation, int miwokImage, int miwokSound) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mMiwokImage = miwokImage;
        mMiwokSound = miwokSound;
    }

    /**
     +     * Get the default translation of the word.
     +     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }
    /**
     +     * Get the Miwok translation of the word.
     +     */
    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getImageResourceId() {
        return mMiwokImage;
    }

    public int getSoundResourceId() {
        return mMiwokSound;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokImage=" + mMiwokImage +
                ", mMiwokSound=" + mMiwokSound +
                '}';
    }

    /**
     +     * Returns whether or not there is an image for this word.
     +     */
    public boolean hasImage() {
         return mMiwokImage != NO_IMAGE_PROVIDED;
   }
}
