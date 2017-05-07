package com.bcuzek.magisterka.infrastructure.domain.dataBase.initialData;

import com.bcuzek.magisterka.R;
import com.bcuzek.magisterka.infrastructure.domain.dataBase.entities.Track;

import lombok.AllArgsConstructor;

/**
 * Created by robert on 06.03.2017.
 */
@AllArgsConstructor
public enum InitialData {
    Beethoven_Fidelio_Overture_Expert_1(R.string.Beethoven_Fidelio_Overture_Expert_1, R.drawable.ic_beethoven,
            R.raw.beethoven_fidelio_berlin_philharmonic_herbert_von_karajan_1_mov_1),
    Beethoven_Fidelio_Overture_Expert_2(R.string.Beethoven_Fidelio_Overture_Expert_1, R.drawable.ic_beethoven,
            R.raw.beethoven_fidelio_berlin_philharmonic_herbert_von_karajan_2_mov_2),
    Beethoven_Symphony_V(R.string.Beethoven_Symphony_V, R.drawable.ic_beethoven,
            R.raw.beethoven5_berlin_philharmonic_herbert_von_karajan_1977_1),
    Beethoven_Symphony_IX(R.string.Beethoven_Symphony_IX, R.drawable.ic_beethoven,
            R.raw.beethoven9_tenor_berlin_philharmonic_claudio_abbado),
    Berioz_Hungarian_march(R.string.Berioz_Hungarian_march, R.drawable.ic_berioz,
            R.raw.beethoven9_tenor_berlin_philharmonic_herbert_von_karajan_1977),
    Berioz_Roman_Carnival_expert_1(R.string.Berioz_Roman_Carnival_expert_1, R.drawable.ic_berioz,
            R.raw.berlioz_carnival_berlin_philharmonic_arthur_nikisch_1_mov_1),
    Berioz_Roman_Carnival_expert_2(R.string.Berioz_Roman_Carnival_expert_2, R.drawable.ic_berioz,
            R.raw.berlioz_carnival_berlin_philharmonic_arthur_nikisch_2_mov_2),
    Berioz_Borodin_Polovestian_expert_1(R.string.Berioz_Borodin_Polovestian_expert_1, R.drawable.ic_berioz,
            R.raw.borodin_polovetsian_berlin_philharmonic_herbert_von_karajan_1_mov_1),
    Berioz_Borodin_Polovestian_expert_2(R.string.Berioz_Borodin_Polovestian_expert_2, R.drawable.ic_berioz,
            R.raw.borodin_polovetsian_berlin_philharmonic_herbert_von_karajan_2_mov_2),
    Berioz_Borodin_Polovestian_expert_3(R.string.Berioz_Borodin_Polovestian_expert_3, R.drawable.ic_berioz,
            R.raw.borodin_polovetsian_berlin_philharmonic_herbert_von_karajan_3_mov_3),
    Berioz_Borodin_Polovestian_expert_4(R.string.Berioz_Borodin_Polovestian_expert_4, R.drawable.ic_berioz,
            R.raw.borodin_polovetsian_berlin_philharmonic_herbert_von_karajan_4_mov_4),
    Berioz_Borodin_Polovestian_expert_5(R.string.Berioz_Borodin_Polovestian_expert_5, R.drawable.ic_berioz,
            R.raw.borodin_polovetsian_berlin_philharmonic_herbert_von_karajan_5_mov_5),
    Berioz_Borodin_Steep_of_Asia(R.string.Berioz_Borodin_Steep_of_Asia, R.drawable.ic_berioz,
            R.raw.borodin_steppes_royal_philharmonic_orchestra_ole_schmidt),
    Brahms_Academic_Overture_expert_1(R.string.Brahms_Academic_Overture_expert_1, R.drawable.ic_brahms,
            R.raw.brahms_academic_berlin_philharmonic_nikolaus_harnoncourt_1_mov_1),
    Brahms_Academic_Overture_expert_2(R.string.Brahms_Academic_Overture_expert_2, R.drawable.ic_brahms,
            R.raw.brahms_academic_berlin_philharmonic_nikolaus_harnoncourt_2_mov_2),
    Brahms_Academic_Overture_expert_3(R.string.Brahms_Academic_Overture_expert_3, R.drawable.ic_brahms,
            R.raw.brahms_academic_berlin_philharmonic_nikolaus_harnoncourt_3_mov_3),
    Brahms_Academic_Overture_expert_4(R.string.Brahms_Academic_Overture_expert_4, R.drawable.ic_brahms,
            R.raw.brahms_academic_berlin_philharmonic_nikolaus_harnoncourt_4_mov_4),
    Brahms_Academic_Overture_expert_5(R.string.Brahms_Academic_Overture_expert_5, R.drawable.ic_brahms,
            R.raw.brahms_academic_berlin_philharmonic_nikolaus_harnoncourt_5_mov_5);

    private final int title;
    private final int icon;
    private final int soundId;

    public Track create() {
        return new Track(ordinal(), soundId, icon, title);
    }
}