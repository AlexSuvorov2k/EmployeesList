package ru.alexsuvorov.employee_service;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.alexsuvorov.employee_service.api.App;
import ru.alexsuvorov.employee_service.dao.EmployeeDao;
import ru.alexsuvorov.employee_service.db.AppDatabase;
import ru.alexsuvorov.employee_service.db.DBAdapter;
import ru.alexsuvorov.employee_service.fragments.SpecialtyListFragment;
import ru.alexsuvorov.employee_service.model.Employee;
import ru.alexsuvorov.employee_service.model.ResponseModel;
import ru.alexsuvorov.employee_service.model.Specialty;
import ru.alexsuvorov.employee_service.utils.Utils;

public class EmployeeService extends AppCompatActivity {
    //private static final String URL = "http://gitlab.65apps.com/65gb/static/raw/master/testTask.json";
    Set<Specialty> set = new HashSet<>();
    ArrayList<Employee> employeeList = new ArrayList<>();
    ArrayList<Specialty> specialtyList = new ArrayList<>(set);
    public DBAdapter mDbAdapter;
    private ProgressDialog pdialog;
    FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database").allowMainThreadQueries().build();
        final EmployeeDao employeeDao = db.employeeDao();
        //mDbAdapter = new DBAdapter(this);
        container = findViewById(R.id.fragmentContainer);
        if (Utils.isNetworkAvailable(this)) {
            pdialog = ProgressDialog.show(this, "Загрузка", "Пожалуйста, подождите");
            App.getApi().getJSON().enqueue(new Callback<ResponseModel>() {

                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    if (response.isSuccessful()) {
                        employeeList.addAll(response.body().getResponse());
                        for (int j = 0; j < employeeList.size(); j++) {
                            Utils.Log("List " + employeeList.get(j).getF_name());
                            Employee employee = new Employee();
                            employee.setF_name(employeeList.get(j).getF_name());
                            employee.setL_name(employeeList.get(j).getL_name());
                            employee.setBirthday(employeeList.get(j).getBirthday());
                            employee.setAge(employeeList.get(j).getAge());
                            employee.setAvatarLink(employeeList.get(j).getAvatarLink());
                            /*for (int i = 0; i < employeeList.get(j).getSpecialty().size(); i++) {
                                Specialty specialty = new Specialty();
                                specialty.setSpecId(employeeList.get(j).getSpecialty().get(i).getSpecId());
                                Utils.Log("SpecialtyID is: " + employeeList.get(j).getSpecialty().get(i).getSpecId());
                                specialty.setSpecName(employeeList.get(j).getSpecialty().get(i).getSpecName());
                                set.add(specialty);
                                Utils.Log("Specialty is: " + specialty);
                            }
                            employee.setSpecialty(specialtyList);*/
                            employeeDao.insert(employee);
                        }
                        /*for (Specialty specialty : set) {
                            mDbAdapter.insertSpecialty(specialty);
                        }*/
                    } else {
                        Utils.Log("response code " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(EmployeeService.this, "Server error", Toast.LENGTH_LONG).show();
                }
            });
            SpecialtyListFragment employeesListFragment = new SpecialtyListFragment();
            FragmentManager fManager = getSupportFragmentManager();
            fManager.beginTransaction()
                    .add(R.id.fragmentContainer, employeesListFragment)
                    .commit();
            pdialog.dismiss();
        } else {
            Toast.makeText(this, "No Network Connection", Toast.LENGTH_LONG).show();
        }
    }
}