package tech.gruppone.stalker.app.view.fragment;

import static java.util.Objects.requireNonNull;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import lombok.Setter;
import tech.gruppone.stalker.app.R;

public class LdapDialogFragment extends DialogFragment {

  @FunctionalInterface
  public interface PositiveListener {
    void onClickPositiveButton(@NonNull String rdn, @NonNull String password);
  }

  @FunctionalInterface
  public interface NegativeListener {
    void onClickNegativeButton();
  }

  @Setter PositiveListener positiveListener = null;
  @Setter NegativeListener negativeListener = null;

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    FragmentActivity activity = requireNonNull(getActivity());
    View dialogView =
        activity
            .getLayoutInflater()
            .inflate(
                R.layout.ldap_credential_dialog_content,
                activity.findViewById(R.id.organizationDetailsLayout),
                false);

    return new MaterialAlertDialogBuilder(activity)
        .setTitle(R.string.ldapCredentialsTitle)
        .setView(dialogView)
        .setPositiveButton(
            R.string.connect,
            (dialog, which) -> {
              if (positiveListener != null) {
                String rdn =
                    requireNonNull(
                            ((TextInputLayout) dialogView.findViewById(R.id.ldapRdnInputLayout))
                                .getEditText())
                        .getText()
                        .toString();

                String password =
                    requireNonNull(
                            ((TextInputLayout)
                                    dialogView.findViewById(R.id.ldapPasswordInputLayout))
                                .getEditText())
                        .getText()
                        .toString();

                positiveListener.onClickPositiveButton(rdn, password);
              }

              dialog.dismiss();
            })
        .setNegativeButton(
            R.string.cancel,
            (dialog, which) -> {
              if (negativeListener != null) {
                negativeListener.onClickNegativeButton();
              }
              dialog.cancel();
            })
        .create();
  }
}
