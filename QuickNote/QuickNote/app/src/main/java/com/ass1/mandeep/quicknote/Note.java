package com.ass1.mandeep.quicknote;

/**
 * Mandeep Singh, 213347007
 * SIT207 Assignment 2
 */
// Note Class to store notes data
public class Note {
    String noteTitle;
    String noteContent;
    String contShort;
    String NoteDate;

    public Note(String title, String content, String contshort, String date)
    {
        noteTitle = title;
        noteContent = content;
        contShort = contshort;
        NoteDate = date;
    }
}
