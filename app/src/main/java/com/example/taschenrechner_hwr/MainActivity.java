package com.example.taschenrechner_hwr;

import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.*;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    private EditText anzeige;
    Button buttonEins, buttonZwei, buttonDrei, buttonVier, buttonFuenf, buttonSechs, buttonSieben, buttonAcht, buttonNeun, buttonZero, buttonA, buttonB, buttonC, buttonD, buttonE, buttonF;
    RadioButton buttonBin, buttonHex, buttonDez, buttonOkt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anzeige = findViewById(R.id.textEingabe);

        //Tastatur Popup bleibt deaktiviert
        anzeige.setShowSoftInputOnFocus(false);

        buttonHex=findViewById(R.id.buttonHex);
        buttonDez=findViewById(R.id.buttonDez);
        buttonOkt=findViewById(R.id.buttonOkt);
        buttonBin=findViewById(R.id.buttonBin);
        buttonEins=findViewById(R.id.buttonEins);
        buttonZwei=findViewById(R.id.buttonZwei);
        buttonDrei=findViewById(R.id.buttonDrei);
        buttonVier=findViewById(R.id.buttonVier);
        buttonFuenf=findViewById(R.id.buttonFuenf);
        buttonSechs=findViewById(R.id.buttonSechs);
        buttonSieben=findViewById(R.id.buttonSieben);
        buttonAcht=findViewById(R.id.buttonAcht);
        buttonNeun=findViewById(R.id.buttonNeun);
        buttonZero=findViewById(R.id.buttonZero);
        buttonA=findViewById(R.id.buttonA);
        buttonB=findViewById(R.id.buttonB);
        buttonC=findViewById(R.id.buttonC);
        buttonD=findViewById(R.id.buttonD);
        buttonE=findViewById(R.id.buttonE);
        buttonF=findViewById(R.id.buttonF);

        //Der Text in dem Anzeigefenster verschwindet nach einer Eingabe automatisch
        anzeige.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getString(R.string.anzeige).equals(anzeige.getText().toString())){
                    anzeige.setText(" ");
                }
            }
        });
    }

    private void updateText(String strToAdd) {
        String oldStr = anzeige.getText().toString();
        int cursorPos = anzeige.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (getString(R.string.anzeige).equals(anzeige.getText().toString())){
            anzeige.setText(strToAdd);
            anzeige.setSelection(cursorPos + 1);
        }
        else {
            anzeige.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            anzeige.setSelection(cursorPos + 1);
        }
    }

        public void buttonZero (View view){
        updateText("0");
        }

        public void buttonEins (View view){
            updateText("1");
        }

        public void buttonZwei (View view){
            updateText("2");
        }

        public void buttonDrei (View view){
            updateText( "3");
        }

        public void buttonVier (View view){
            updateText("4");
        }

        public void buttonFuenf (View view){
            updateText("5");
        }

        public void buttonSechs (View view){
            updateText("6");
        }

        public void buttonSieben (View view){
            updateText("7");
        }
        public void buttonAcht (View view){
            updateText("8");
        }

        public void buttonNeun (View view){
            updateText("9");
        }

        public void buttonExponent (View view){
            updateText("^");
        }

       /* public void buttonWurzel (View view){
            updateText("√");
        }
        */

        public void buttonAddition (View view){
            updateText("+");
        }

        public void buttonSubtraktion (View view){
            updateText("-");
        }

        public void buttonDivision (View view){
            updateText("÷");
        }

        public void buttonMultiplikation (View view){
            updateText("×");
        }

        public void buttonKomma (View view){
            updateText(",");
        }

        public void buttonLoeschen (View view){
            anzeige.setText("");
        }

        public void buttonKlammer (View view) {
            int cursorPos = anzeige.getSelectionStart();
            int offeneKlammer = 0;
            int geschlosseneKlammer = 0;
            int textLen = anzeige.getText().length();

            for (int i = 0; i < cursorPos; i++) {
                if (anzeige.getText().toString().substring(i, i + 1).equals("(")) {
                    offeneKlammer += 1;
                }
                if (anzeige.getText().toString().substring(i, i + 1).equals(")")) {
                    geschlosseneKlammer += 1;
                }
            }

            if (offeneKlammer == geschlosseneKlammer || anzeige.getText().toString().substring(textLen-1, textLen).equals("(")){
                updateText("(");
            }
            else if (geschlosseneKlammer < offeneKlammer && !anzeige.getText().toString().substring(textLen-1, textLen).equals("(")){
                updateText(")");
            }
            anzeige.setSelection(cursorPos + 1);
        }

        public void buttonA (View view){
            updateText("A");
        }

        public void buttonB (View view){
            updateText("B");
        }

        public void buttonC (View view){
            updateText("C");
        }

        public void buttonD (View view){
            updateText("D");
        }

        public void buttonE (View view){
            updateText("E");
        }

        public void buttonF (View view){
            updateText("F");
        }

        public void buttonPlusMinus (View view){
        }

        public void buttonGleich (View view){
            String userExp = anzeige.getText().toString();

            userExp = userExp.replaceAll("÷", "/");
            userExp = userExp.replaceAll("×", "*");
            userExp = userExp.replaceAll(",", ".");

            Expression exp = new Expression(userExp);

            String ergebnis = String.valueOf(exp.calculate());

            anzeige.setText(ergebnis);
            anzeige.setSelection(ergebnis.length());

        }

        public void buttonZurueck (View view){
            int cursorPos = anzeige.getSelectionStart();
            int textLen = anzeige.getText().length();

            if (cursorPos !=0 && textLen !=0){
                SpannableStringBuilder auswahl = (SpannableStringBuilder) anzeige.getText();
                auswahl.replace(cursorPos - 1, cursorPos, "");
                anzeige.setText(auswahl);
                anzeige.setSelection(cursorPos - 1);
            }
        }

        public void konvertieren(View view) {
            boolean checked = ((RadioButton) view).isChecked();
            String number = anzeige.getText().toString();
            if (checked) {
                switch (view.getId()) {
                    case R.id.buttonBin:
                        buttonEins.setEnabled(true);
                        buttonZwei.setEnabled(false);
                        buttonDrei.setEnabled(false);
                        buttonVier.setEnabled(false);
                        buttonFuenf.setEnabled(false);
                        buttonSechs.setEnabled(false);
                        buttonSieben.setEnabled(false);
                        buttonAcht.setEnabled(false);
                        buttonNeun.setEnabled(false);
                        buttonZero.setEnabled(true);
                        buttonA.setEnabled(false);
                        buttonB.setEnabled(false);
                        buttonC.setEnabled(false);
                        buttonD.setEnabled(false);
                        buttonE.setEnabled(false);
                        buttonF.setEnabled(false);
                        String binaer = Integer.toBinaryString(Integer.parseInt(number));
                        anzeige.setText(binaer);
                        break;
                    case R.id.buttonOkt:
                        buttonEins.setEnabled(true);
                        buttonZwei.setEnabled(true);
                        buttonDrei.setEnabled(true);
                        buttonVier.setEnabled(true);
                        buttonFuenf.setEnabled(true);
                        buttonSechs.setEnabled(true);
                        buttonSieben.setEnabled(true);
                        buttonAcht.setEnabled(false);
                        buttonNeun.setEnabled(false);
                        buttonZero.setEnabled(true);
                        buttonA.setEnabled(false);
                        buttonB.setEnabled(false);
                        buttonC.setEnabled(false);
                        buttonD.setEnabled(false);
                        buttonE.setEnabled(false);
                        buttonF.setEnabled(false);
                        String oktal = Integer.toOctalString(Integer.parseInt(number));
                        anzeige.setText(oktal);
                        break;
                    case R.id.buttonDez:
                        buttonEins.setEnabled(true);
                        buttonZwei.setEnabled(true);
                        buttonDrei.setEnabled(true);
                        buttonVier.setEnabled(true);
                        buttonFuenf.setEnabled(true);
                        buttonSechs.setEnabled(true);
                        buttonSieben.setEnabled(true);
                        buttonAcht.setEnabled(true);
                        buttonNeun.setEnabled(true);
                        buttonZero.setEnabled(true);
                        buttonA.setEnabled(false);
                        buttonB.setEnabled(false);
                        buttonC.setEnabled(false);
                        buttonD.setEnabled(false);
                        buttonE.setEnabled(false);
                        buttonF.setEnabled(false);
                        String dezimal = Integer.toString(Integer.parseInt(number));
                        anzeige.setText(dezimal);
                        break;
                    case R.id.buttonHex:
                        buttonEins.setEnabled(true);
                        buttonZwei.setEnabled(true);
                        buttonDrei.setEnabled(true);
                        buttonVier.setEnabled(true);
                        buttonFuenf.setEnabled(true);
                        buttonSechs.setEnabled(true);
                        buttonSieben.setEnabled(true);
                        buttonAcht.setEnabled(true);
                        buttonNeun.setEnabled(true);
                        buttonZero.setEnabled(true);
                        buttonA.setEnabled(true);
                        buttonB.setEnabled(true);
                        buttonC.setEnabled(true);
                        buttonD.setEnabled(true);
                        buttonE.setEnabled(true);
                        buttonF.setEnabled(true);
                        String hexadezimal = Integer.toHexString(Integer.parseInt(number));
                        anzeige.setText(hexadezimal);
                        break;
                }
        }}
}