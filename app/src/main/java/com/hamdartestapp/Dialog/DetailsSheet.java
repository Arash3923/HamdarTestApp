package com.hamdartestapp.Dialog;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.hamdartestapp.Api.Models.AppModel;
import com.hamdartestapp.Utility.FileSaver;
import com.hamdartestapp.databinding.SheetDetailsBinding;


public class DetailsSheet extends ConfigSheet {
    SheetDetailsBinding binding;
    AppModel model;

    public static void Show(AppCompatActivity activity, AppModel model) {
        DetailsSheet bottomSheetDialog = new DetailsSheet();
        bottomSheetDialog.show(activity.getSupportFragmentManager(), "Details_Sheet");
        bottomSheetDialog.model = model;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = SheetDetailsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        setConfigSheet(getDialog(), true, true);

        try {
            String Description=model.getId()+"\n"+model.getAppName()+"\n"+model.getPkgName()+"\n"+model.getIconUrl()+"\n"+model.getCat()+
                    "\n"+model.getCreatedAt()+"\n"+model.getUpdatedAt()+"\n";
            binding.title.setText(model.getAppName());
            binding.description.setText(Description);


            binding.btn.setOnClickListener(view1 -> {
                String text = AppModel.serialize(model);
                String fileName = "HamdarTestApp_"+model.getAppName() +".txt";
                FileSaver fileSaver = new FileSaver(requireActivity());
                fileSaver.saveTextToFile(fileName, text);
            });

        } catch (Exception e) {
            dismiss();
        }
        return view;
    }
}






