package com.shs.vetterhealth.pillnotifier;

import android.content.Context;

import androidx.annotation.NonNull;

import com.shs.vetterhealth.pillnotifier.data.source.MedicineRepository;
import com.shs.vetterhealth.pillnotifier.data.source.local.MedicinesLocalDataSource;


/**
 * Created by gautam on 13/07/17.
 */

public class Injection {

    public static MedicineRepository provideMedicineRepository(@NonNull Context context) {
        return MedicineRepository.getInstance(MedicinesLocalDataSource.getInstance(context));
    }
}
