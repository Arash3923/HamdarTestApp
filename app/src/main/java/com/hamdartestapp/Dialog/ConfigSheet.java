package com.hamdartestapp.Dialog;

import android.app.Dialog;
import android.widget.FrameLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hamdartestapp.R;

import java.util.Objects;

public class ConfigSheet extends BottomSheetDialogFragment
{
    public static void setConfigSheet(Dialog sheet, boolean isCansel, boolean isDrag) {
        Objects.requireNonNull(Objects.requireNonNull(sheet).getWindow()).setBackgroundDrawableResource(R.drawable.sheet_back_shadow);

        sheet.setOnShowListener(dialog -> {
            FrameLayout bottomSheet = sheet.findViewById(com.google.android.material.R.id.design_bottom_sheet);
            sheet.setCancelable(isCansel);
            BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
            behavior.setDraggable(isDrag);
            behavior.setSkipCollapsed(false);
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        });
    }
}




