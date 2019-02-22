package br.com.franciscochaves.festafimdeano.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.franciscochaves.festafimdeano.R;
import br.com.franciscochaves.festafimdeano.constants.FimDeAnoConstants;
import br.com.franciscochaves.festafimdeano.util.SecurityPreferences;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHold mViewHold = new ViewHold();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHold.textToday = (TextView) findViewById(R.id.text_today);
        this.mViewHold.textDayLeft = (TextView) findViewById(R.id.text_days_left);
        this.mViewHold.buttonConfirm = (Button) findViewById(R.id.button_confirm);

        this.mViewHold.buttonConfirm.setOnClickListener(this);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.verifyPresence();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.button_confirm){

            String presence = this.mSecurityPreferences.getStoreString(FimDeAnoConstants.PRESENCE);

            //Lógica de navegação
            Intent intent = new Intent(this, DetailsActivity.class);

            intent.putExtra(FimDeAnoConstants.PRESENCE, presence);

            startActivity(intent);
        }

    }

    private void verifyPresence() {
        String presence = this.mSecurityPreferences.getStoreString(FimDeAnoConstants.PRESENCE);
        if(presence.equals("")){
            this.mViewHold.buttonConfirm.setText(R.string.nao_confirmado);
        }else if(presence.equals(FimDeAnoConstants.CONFIRMED_WILL_GO)){
            this.mViewHold.buttonConfirm.setText(R.string.sim);
        }else{
            this.mViewHold.buttonConfirm.setText(R.string.nao);
        }
    }


    private static class ViewHold{
        TextView textToday;
        TextView textDayLeft;
        Button buttonConfirm;
    }
}
