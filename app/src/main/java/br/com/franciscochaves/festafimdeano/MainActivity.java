package br.com.franciscochaves.festafimdeano;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHold mViewHold = new ViewHold();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHold.textToday = (TextView) findViewById(R.id.text_today);
        this.mViewHold.textDayLeft = (TextView) findViewById(R.id.text_days_left);
        this.mViewHold.buttonConfirm = (Button) findViewById(R.id.button_confirm);

        this.mViewHold.buttonConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.button_confirm){
            //Lógica de navegação
            Intent intent = new Intent(this, DetailsActivity.class);
            startActivity(intent);
        }

    }

    private static class ViewHold{
        TextView textToday;
        TextView textDayLeft;
        Button buttonConfirm;
    }
}
