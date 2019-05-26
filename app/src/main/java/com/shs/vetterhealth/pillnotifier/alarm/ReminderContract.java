package com.shs.vetterhealth.pillnotifier.alarm;

import com.shs.vetterhealth.pillnotifier.BasePresenter;
import com.shs.vetterhealth.pillnotifier.BaseView;
import com.shs.vetterhealth.pillnotifier.data.source.History;
import com.shs.vetterhealth.pillnotifier.data.source.MedicineAlarm;

/**
 * Created by gautam on 13/07/17.
 */

public interface ReminderContract {

    interface View extends BaseView<Presenter> {

        void showMedicine(MedicineAlarm medicineAlarm);

        void showNoData();

        boolean isActive();

        void onFinish();

    }

    interface Presenter extends BasePresenter {

        void finishActivity();

        void onStart(long id);

        void loadMedicineById(long id);

        void addPillsToHistory(History history);

    }
}
