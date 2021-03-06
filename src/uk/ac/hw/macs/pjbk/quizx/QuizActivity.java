package uk.ac.hw.macs.pjbk.quizx;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity 
implements OnClickListener{
    private QuizApplication application;
    private Button historyButton;
    private Button mathsButton;
    private Button scienceButton;
    private Button geographyButton;
    private TextView summary;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        application = (QuizApplication) this.getApplication();
        scienceButton = (Button) findViewById(R.id.science_button);
        scienceButton.setOnClickListener(this);
        geographyButton = (Button) findViewById(R.id.geography_button);
        geographyButton.setOnClickListener(this);
        historyButton = (Button) findViewById(R.id.history_button);
        historyButton.setOnClickListener(this);
        mathsButton = (Button) findViewById(R.id.maths_button);
        mathsButton.setOnClickListener(this);
        summary = (TextView) findViewById(R.id.overall_summary);
        generateSummary();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        generateSummary();
    }

    private void generateSummary()
    {
        summary.setText( application.getQuestionsCorrect() + " questions correct "
                         + application.getQuestionsAttempted() + " attempted.");
    }

    public void onClick(View arg0)
    {
        if ( arg0 == historyButton ) {
            Log.i("QUIZ", "History selected");
            Toast.makeText(getApplicationContext(), "History button pressed", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, uk.ac.hw.macs.pjbk.quizx.MultiChoiceQuestionsActivity.class));
        }
        if ( arg0 == mathsButton ) {
            Log.i("QUIZ", "Maths selected");
            Toast.makeText(getApplicationContext(), "Maths button pressed", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, uk.ac.hw.macs.pjbk.quizx.NumericQuestion.class));
        }
        if ( arg0 == geographyButton ) {
            Log.i("QUIZ", "Geography selected");
            Toast.makeText(getApplicationContext(), "Geography button pressed", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, uk.ac.hw.macs.pjbk.quizx.GeographyQuestion.class));
        }
        if ( arg0 == scienceButton ) {
            Log.i("QUIZ", "Science selected");
            Toast.makeText(getApplicationContext(), "Science button pressed", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, uk.ac.hw.macs.pjbk.quizx.ScienceQuestion.class));
        }
    }
}
