package br.com.franciscochaves.festafimdeano.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import br.com.franciscochaves.festafimdeano.R;
import br.com.franciscochaves.festafimdeano.constants.FimDeAnoConstants;
import br.com.franciscochaves.festafimdeano.util.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHold mViewHold = new ViewHold();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHold.checkParticipate = (CheckBox) findViewById(R.id.check_participate);

        this.mViewHold.checkParticipate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.check_participate){
            //Lógica para salvar a resposta
            if(this.mViewHold.checkParticipate.isChecked()){
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE, FimDeAnoConstants.CONFIRMED_WILL_GO);
            }else{
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE, FimDeAnoConstants.CONFIRMED_WONT_GO);
            }
        }
    }

    private static class ViewHold{
        CheckBox checkParticipate;
    }
}
