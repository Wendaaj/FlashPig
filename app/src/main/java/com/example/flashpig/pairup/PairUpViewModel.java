package com.example.flashpig.pairup;

        import androidx.lifecycle.ViewModel;

        import com.example.flashpig.Model.PairUp;

        import java.util.Random;

public class PairUpViewModel extends ViewModel {

    PairUp pairUp;
    Random rand;

    public PairUpViewModel(PairUp pairUp, Random rand) {
        this.pairUp = pairUp;
        this.rand = rand;
    }


}
