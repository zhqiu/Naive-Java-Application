import javafx.application.Application; 
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;  
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.ArrayList;

public class DictionaryUI extends Application 
{
	private TextField tfInputBox = new TextField();         //输入框
	private TextArea taAssociationBox = new TextArea();     //联想框
	private TextArea taOutputBox = new TextArea();          //主显框
	
	private Button btSearch = new Button("Search");         //搜索按钮
	
	private DictionaryData myDictionary;
	
	
	@Override   //重写Application类中的start方法
	public void start(Stage primaryStage)
	{
		//创建UI
		HBox hbox = new HBox();    
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(new Label("Please input:"),tfInputBox,btSearch);
		
		taAssociationBox.setEditable(false);
		taAssociationBox.setPrefColumnCount(10);
		taAssociationBox.setPrefRowCount(20);
		
		taOutputBox.setEditable(false);
		taOutputBox.setPrefColumnCount(20);
		taOutputBox.setPrefRowCount(20); 
		
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(taOutputBox);
		borderPane.setLeft(taAssociationBox);
		borderPane.setTop(hbox);
		
		//读入数据
		try {
			myDictionary=new DictionaryData("dictionary.txt");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return;
		}
		
		//事件处理
		btSearch.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				Entry entry_match=myDictionary.findEntry(tfInputBox.getText());
				taOutputBox.setText(entry_match.toString());
				if (entry_match.getID()==0)                           // 若未找到该单词
				{ 
					String similarWords=myDictionary.findSimilarWord(tfInputBox.getText());
					taAssociationBox.setText(similarWords);
					String[] smlWord=similarWords.split("\n");
					String theMostSimilarWord=smlWord[0];
					taOutputBox.setText("您是不是要翻译"+theMostSimilarWord+"单词？");
				}
			}
		}); 
		
		//创建联想框监听器  
		tfInputBox.textProperty().addListener(new InvalidationListener(){
			public void invalidated(Observable ov){
				taAssociationBox.setText(myDictionary.findAssociationWord(tfInputBox.getText())); 
			}
		});
		
		//创建一个场景scene，并将其置于stage上
		Scene scene = new Scene(borderPane,400,300);
		primaryStage.setTitle("*****Dictionary*****");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}