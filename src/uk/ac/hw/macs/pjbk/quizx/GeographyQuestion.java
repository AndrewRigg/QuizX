package uk.ac.hw.macs.pjbk.quizx;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class GeographyQuestion extends Activity
implements OnClickListener{
	private TextView question;
	private TextView summary;
	private InputStream xmlFile;
	private SAXParser xmlReader;
	private Vector<MCQuestion> questions;
	private RadioGroup opts;
	private MCQuestion currentQuestion;
	private int noCorrect;
	private int noAnswered;
	private int noQuestions = 3;
	private QuizApplication application;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multichoice);
        
        try{
        	xmlFile = QuizApplication.getAppContext().getAssets().open("geography.xml");
        }catch (IOException e){
        	System.err.println(e);
        }
        SAXParser.parse(xmlFile);
        Log.i("MCQ", "MultiChoice starts");
        application = (QuizApplication) this.getApplication();
        opts = (RadioGroup) findViewById(R.id.opts);
        Button sub = (Button) findViewById(R.id.submit);
        question = (TextView) findViewById(R.id.question_text);
        summary = (TextView) findViewById(R.id.multichoice_summary);  
        noCorrect = 0;
        noAnswered = 0;
        sub.setOnClickListener(this);
        initialiseQuestions();
        generateSummary();
        generateQuestion(0);
    }

	private void generateQuestion(int noQ) {
		opts.removeAllViews();
		currentQuestion = questions.get(noQ);
		question.setText(currentQuestion.getQuestion());
		int i = 1;
		for( String ans: currentQuestion.getAnswers()) {
			RadioButton rb = new RadioButton(this);
			rb.setText(ans);
			rb.setId(i++);
			opts.addView(rb);
		}
	}
	
	private void initialiseQuestions() {
		questions = new Vector<MCQuestion>();
		//String st =  SAXParser.parse(xmlFile);
		xmlReader = new SAXParser();
		
		MCQuestion q1 = new MCQuestion(xmlReader.toString());
		//q1.addAnswer(xmlFile.toString(),false);
		//q1.addAnswer(st, false);
		//q1.addAnswer(SAXParser.parse(xmlFile).get(0).getAnswer(0), false);
		q1.addAnswer(xmlReader.toString(), false);
		q1.addAnswer("G1", false);
		q1.addAnswer("G2", false);
		q1.addAnswer("G3", true);
		q1.addAnswer("G4", false);
		questions.add(q1);	
		q1 = new MCQuestion("G5");
		q1.addAnswer("Edward Whymper", false);
		q1.addAnswer("Edmund Hillary", true);
		q1.addAnswer("Chris Bonnington", false);
		questions.add(q1);	
		q1 = new MCQuestion("Who first reached the South Pole?");
		q1.addAnswer("Captain Scott", false);
		q1.addAnswer("Roald Amundsen", true);
		q1.addAnswer("Edmund Hillary", false);
		q1.addAnswer("Robert Peary", false);
		questions.add(q1);	
		// more questions could be added
		}
	
	   private void generateSummary()
	    {
	    	summary.setText( noCorrect + " answers out of " + noAnswered 
	    			+ " attempted of " +noQuestions + " questions");
	    }

	public void onClick(View arg0) {		
		noAnswered++;
        application.incrementQuestionsAttempted();
		int id;
		id = opts.getCheckedRadioButtonId();
		if( currentQuestion.isCorrect(id)){
			System.out.println(noCorrect);
			noCorrect++;
	        application.incrementQuestionsCorrect();
		}
		generateSummary();
		if (noAnswered == noQuestions)
			finish();
		else
			generateQuestion(noAnswered);
	}

}
