package br.com.franciscochaves.festafimdeano.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.franciscochaves.festafimdeano.R;
import br.com.franciscochaves.festafimdeano.constants.FimDeAnoConstants;
import br.com.franciscochaves.festafimdeano.util.SecurityPreferences;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHold mViewHold = new ViewHold();
    private SecurityPreferences mSecurityPreferences;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Customizando a barra superior padrão
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        this.mViewHold.textToday = (TextView) findViewById(R.id.text_today);
        this.mViewHold.textDayLeft = (TextView) findViewById(R.id.text_days_left);
        this.mViewHold.buttonConfirm = (Button) findViewById(R.id.button_confirm);

        this.mViewHold.buttonConfirm.setOnClickListener(this);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHold.textToday.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));

        String daysLeft = String.format("%s %s", String.valueOf(this.getDaysLeftToEndOfYear()), getString(R.string.dias));
        this.mViewHold.textDayLeft.setText(daysLeft);
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

    private int getDaysLeftToEndOfYear() {
        Calendar calendarToday = Calendar.getInstance();
        int today = calendarToday.get(Calendar.DAY_OF_YEAR);

        Calendar calendarLastDay = Calendar.getInstance();
        int dayDecember31 = calendarLastDay.getActualMaximum(Calendar.DAY_OF_YEAR);

        return dayDecember31 - today;
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

    // Quando a activity se torna visível para o usuário
    @Override
    protected void onStart() {
        super.onStart();
    }

    // A activity está pronta para ser manipulada, para receber valores
    @Override
    protected void onResume() {
        super.onResume();

        this.verifyPresence();
    }

    // A primeira notificação que a activity está saindo do estado ativa
    @Override
    protected void onPause() {
        super.onPause();
    }

    // Neste método, pode adicionar processamento mais pesado, salvar dados por exemplo
    @Override
    protected void onStop() {
        super.onStop();
    }

    // Quando a activity for deslocada da mémoria, quando está sem capacidade para execução
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private static class ViewHold{
        TextView textToday;
        TextView textDayLeft;
        Button buttonConfirm;
    }
}
