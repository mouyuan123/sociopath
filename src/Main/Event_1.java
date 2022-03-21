package Main;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author MC
 */
public class Event_1 {

    //Instant Variables
    private boolean isCorrect; // True if the answer is correct & false if the answer is wrong
    private Random r = new Random();
    private Student answer;
    private Student ask;
    private ArrayList<String> event1Dialog = new ArrayList<>();

    //Constructor
    public Event_1(Student ask,Student answer) {
        this.isCorrect = false; // isCorrect = false by default;
        this.ask = ask;
        this.answer = answer;
        AskLabQuestion();
    }

    //Methods Implementation
    // Only stranger may ask other students
    public void AskLabQuestion() {
       

            event1Dialog.add(ask.getName() + " : " + answer.getName() + " can you try to help me to solve this lab question?");
            event1Dialog.add(answer.getName() + " : " + "Sure!");
            if (answer.isIsProgrammingExpert()) {
                // The expert may always solve the lab question
                this.isCorrect = true;
                event1Dialog.add(answer.getName() + " : I can solve this quesion!");
                event1Dialog.add(ask.getName() + " : Thank you so much!" + "\n");
            } // If the student is not an expert, he/she may answer the lab question wrongly easily, Hence isCorrect = false;
            else {
                event1Dialog.add(answer.getName() + " : Oh, I am so sorry that I cant solve this question");
                event1Dialog.add(ask.getName() + " : Nevermind! It is fine" + "\n");
            }
            befriend(ask, answer);
            if (viralToOtherFriends(ask, answer)) {
                // More friends of ask -> answer ( rep increses )
                event1Dialog.add(answer.getName() + " has the opportunity to make more friends now!" + "\n");
            }
    }

    public void befriend(Student ask, Student answer) {
        if (isCorrect) {
            // If the answer is correct, ask -> answer (reputation points = 10)
            ask.setReputation(answer, 10);
        } 
        else {
            // If the answer is correct, ask -> answer (reputation points = 2)
            ask.setReputation(answer, 2);
        }
        event1Dialog.add("After going back room...");
        if(ask.isIsProgrammingExpert()){
            answer.setReputation(ask, 3); // Assume ask is expert but he still have some question need others' helps
            event1Dialog.add("(In "+answer.getName()+" own thought) : "+ask.getName()+" is actually a programming expert, but he still cant solve some questions!\n");
        }
        else if(!ask.isIsProgrammingExpert()){
            answer.setReputation(ask, 2); // Assume ask is not a programming expert and always need others helps
            event1Dialog.add("(In "+answer.getName()+" own thought) : "+ask.getName()+" is actually not a programming expert, that's why he ask me qeustion!\n");
        }
        // Once the stranger queries a lab question, ask & answer will automatically become both friends
        ask.addFriends(answer); 
        answer.addFriends(ask);
    }

    public boolean viralToOtherFriends(Student ask, Student answer) {
        // If the ask -> answer (rep = 10)
        if (ask.getReputation().get(answer) == 10) {
            //event1Dialog.add(ask.getName()+" : I think I should viral "+answer.getName());
            event1Dialog.add("In the next day, "+ask.getName()+" is viraling...");
            for (Student i : ask.getReputation().keySet()) {
                // Viral to all his/her friends
                if (!i.getName().equals(answer.getName())) {
                    event1Dialog.add(ask.getName() + " : " + i.getName() + ", " + answer.getName() + " is very good in programming!");
                    if (i.inRepList(answer)) {
                        // They the friend of ask is also the friend of answer, i -> answer ( original +1 )
                        i.setReputation(answer, i.getReputation().get(answer) + 2);
                    } else {
                        i.setReputation(answer, 1);
                    }
                }
                checkRepPoint(i, answer);
            }
            event1Dialog.add("");
        } // If the ask -> answer (rep = 2) && very lazy
        else if (ask.getReputation().get(answer) == 2 && answer.getDivingRate() > 50) {
            // ask will not viral answer to any of his/her friends
            event1Dialog.add(ask.getName()+" : I think I better dont tell my friends about "+answer.getName());
            return false;
        } // If the ask -> answer (rep = 2) but he can put effort in doing assignment
        else if (ask.getReputation().get(answer) == 2 && answer.getDivingRate() < 50) {
            ArrayList<Student> friendList = new ArrayList(ask.getFriends()); // Generate a friend list from ask
            ArrayList<Student> repeat = new ArrayList<>(); // To check whether same friend is invoked
            // The answer him/herself also in the ask's friendlist ( Need to be excluded )
            if(friendList.size() > 2){ 
            // Although, answer is not good in programming, since he is rajin, ask will viral answer to few of his/her friends
            event1Dialog.add("In the next day, "+ask.getName()+" is viraling...");
            repeat.add(answer); // This avoid answer to be got & printed
            int Min = 1;
            int Max = friendList.size() - 2; // The answer is also inside the list of ask after asking lab question, hence answer need to be excluded
            int NumOfViral = r.nextInt(Max - Min + 1) + Min; // To make sure not viraling to all friends & viral to at least 1 friend (range from 1 to friendList size -1)
            while (NumOfViral > 0) {
                @SuppressWarnings("unchecked")
                Student temp = (Student) friendList.get(r.nextInt(friendList.size()));
                while (repeat.contains(temp)) {
                    temp = (Student) friendList.get(r.nextInt(friendList.size())); // To avoid viralling to same friends
                }
                repeat.add(temp);
                event1Dialog.add(ask.getName() + " : " + temp.getName() + ", " + answer.getName() + " is not really good in programming but he can still be cooperative in the assignment!");
                if (temp.inRepList(answer)) {
                    temp.setReputation(answer, temp.getReputation().get(answer) + 1);
                } 
                else {
                    temp.setReputation(answer, 1);
                }
                checkRepPoint(temp, answer);
                NumOfViral--;
            }
            event1Dialog.add("");
        }
            // Assume not to viral if only 1 friend
            else{
                event1Dialog.add(ask.getName()+": I have only one friend, so I better dont viral "+answer.getName()+" to "+friendList.get(0).getName());
                return false;
            }
        }
        return true; // ask viral answer to his/her other friends
    }

    // To check whether answer is inside ask reputation list && they are friends already if a -> b (rep >= 2)

    //To check whether the reputation points > 10
    public boolean checkRepPoint(Student a, Student b) {
        if(a.inRepList(b)){
        if (a.getReputation().get(b) > 10) {
            a.setReputation(b, 10);
        } 
        else if(a.getReputation().get(b) < 1){
            a.setReputation(b, 1);
        }
        return true;
        }
        else{
            return false;
        }
    }

    public Student getAnswer() {
        return answer;
    }

    public Student getAsk() {
        return ask;
    }

    public ArrayList<String> getEvent1Dialog() {
        return event1Dialog;
    }

    public void setEvent1Dialog(ArrayList<String> event1Dialog) {
        this.event1Dialog = event1Dialog;
    }
    
   

}
