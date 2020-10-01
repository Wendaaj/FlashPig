package com.example.flashpig.memory;

import androidx.lifecycle.ViewModel;

import com.example.flashpig.Model.Memory;

import java.util.Random;

public class MemoryViewModel extends ViewModel {

    Memory memory;
    Random rand;

    public MemoryViewModel(Memory memory, Random rand) {
        this.memory = memory;
        this.rand = rand;
    }


}
