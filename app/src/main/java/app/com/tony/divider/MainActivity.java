package app.com.tony.divider;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;
import java.text.NumberFormat;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();
    private static final NumberFormat integerFormat = NumberFormat.getIntegerInstance();

    Spinner spinner;
    Spinner spinner2;
    Spinner spinner3;

    private EditText a;
    private TextView b;
    private TextView c;
    private TextView d;

    private String firstBusser;
    private String secondRunner;
    private String thirdPercentage;

    Button btn;


    int workers= 0;



    int x;
    int y;
    double summer;

    double fee;
    double splitTips;
    double totalTips;
    double myfee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        spinner = (Spinner) findViewById(R.id.spinner); ///

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.bussers, android.R.layout.simple_spinner_item);///
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(MainActivity.this, R.array.foodRunners, android.R.layout.simple_spinner_item);
        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(MainActivity.this, R.array.percentage, android.R.layout.simple_spinner_item);

        spinner.setAdapter(adapter);//
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter3);

        a = (EditText) findViewById(R.id.editTextA);
        b = (TextView) findViewById(R.id.textViewB);
        c = (TextView) findViewById(R.id.textViewC);
        d = (TextView) findViewById(R.id.textViewD);


        a.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

                try {


                    String num = a.getText().toString();
                    totalTips = (double) Integer.parseInt(num);

                    spinner.setOnItemSelectedListener(onItemSelectedListener0);
                    spinner2.setOnItemSelectedListener(onItemSelectedListener1);
                    spinner3.setOnItemSelectedListener(onItemSelectedListener2);
                }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), " To many backspaces ",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });



        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        try
                        {
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                        }
                        catch (NumberFormatException e)
                        {
                            Toast.makeText(getApplicationContext(), "Invalid Amount, Sorry Try again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    AdapterView.OnItemSelectedListener onItemSelectedListener0
            = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            String s0 = (String) parent.getItemAtPosition(position);
            int partOne = Integer.parseInt(s0);
            workers += partOne;
            x = partOne;

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    AdapterView.OnItemSelectedListener onItemSelectedListener1
            = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            String s1 = (String) parent.getItemAtPosition(position);
            int partTwo = Integer.parseInt(s1);
            workers += partTwo;
            y = partTwo;
            splitTips = totalTips / workers;
            fee = splitTips;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }

    };

    AdapterView.OnItemSelectedListener onItemSelectedListener2
            = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            String s2 = (String) parent.getItemAtPosition(position);
            double three = fee *((double) Integer.parseInt(s2)/100);
            myfee = three; // percentage fee;
            b.setText(currencyFormat.format(three));
            double busserTips = fee - myfee;
            Double mee = new Double(busserTips);
            c.setText(currencyFormat.format(mee));

            summer = myfee * x;
            double runnerTips;
            if(y !=1)
            {
                runnerTips = fee+ summer/y;
            }
            else
            {
                runnerTips = fee+ summer;
            }



            Double mee2 = new Double(runnerTips);
            d.setText(currencyFormat.format(mee2));


        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }

    };

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

//        TextView myText = (TextView) view;
//        Toast.makeText(this, "You Selected "+ myText.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
