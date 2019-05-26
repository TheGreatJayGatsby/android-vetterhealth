package com.shs.vetterhealth.pillnotifier.medicine;

import com.shs.vetterhealth.pillnotifier.BasePresenter;
import com.shs.vetterhealth.pillnotifier.BaseView;
import com.shs.vetterhealth.pillnotifier.data.source.MedicineAlarm;

import java.util.List;

/**
 * Created by gautam on 13/07/17.
 */

public interface MedicineContract {

    interface View extends BaseView<Presenter>{

        void showLoadingIndicator(boolean active);

        void showMedicineList(List<MedicineAlarm> medicineAlarmList);

        void showAddMedicine();

        void showMedicineDetails(long medId, String medName);

        void showLoadingMedicineError();

        void showNoMedicine();

        void showSuccessfullySavedMessage();

        boolean isActive();


    }

    interface Presenter extends BasePresenter{

        void onStart(int day);

        void reload(int day);

        void result(int requestCode, int resultCode);

        void loadMedicinesByDay(int day, boolean showIndicator);


        void addNewMedicine();

    }
}
