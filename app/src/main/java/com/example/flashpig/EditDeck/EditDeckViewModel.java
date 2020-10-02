package com.example.flashpig.EditDeck;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flashpig.Model.Card;

import java.util.List;

public class EditDeckViewModel extends ViewModel {

    MutableLiveData<List<Card>> listMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Card>> getListMutableLiveData() {
        return listMutableLiveData;
    }
}
