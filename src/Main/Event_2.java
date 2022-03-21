package Main;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author MC
 */
// For chit-chatting
// Assume "talked" will never know he is being talked about good/bad by others
public class Event_2 {

    // Instant Variables
    private Random r = new Random();
    private final Student talked; // The one to be talked about good/bad
    private final Student newFriend;
    private String badMessage = "";
    private Student enemy; // The "newFriend" becomes the first enemy when he purposely lie & destroy "talked"
    private ArrayList<Student> store = new ArrayList<>(); // To store the students that tell & being talked about
    private boolean lie = false; // true only if there is any lying happens
    private String messageToPropagate = "";
    private boolean good = false; // false if good of talked is propagated and true if bad of talked is propagated
    private boolean isPropagate = false; // true once the message is propagated ("newFriend" -> friends -> other friends)
    private ArrayList<String> print = new ArrayList<String>();

    public ArrayList<String> getPrint() {
        return print;
    }

    //Constructor
    public Event_2(Student talked, Student newFriend) {
        this.talked = talked; // Put the student that will be propagated in this event
        this.newFriend = newFriend; // The new friend after answering his lab question
        decideChatting(newFriend,newFriend.getFriends());
    }

    /*
    Function : It helps to decide whether the student will chat with friends
    @param : a is the student from the Event 1 who just answer a stranger a lab question
     */
    public void decideChatting(Student a, ArrayList<Student> friendList) {
        friendList = a.getFriends();
        ArrayList<Student> temp = new ArrayList<>(); // To avoid putting the student that ask him/her lab question into the list
        for (int i = 0; i < friendList.size(); i++) {
            if (friendList.get(i).equals(talked)) {
                continue;
            } // If this friend heard / propagate about "talked" before
            else if (store.contains(friendList.get(i))) {
                continue;
            } else {
                temp.add(a.getFriends().get(i));
            }
        }
        if (!temp.isEmpty()) {
            int NumOfFriendsMeet = r.nextInt(temp.size() - 1 + 1) + 1; // To make sure he/she meet at least one friend for chit-chatting
            print.add("After a few days....." + "\n");
            print.add("While walking, " + a.getName() + " meet his friend/friends, ");
            ArrayList<Student> chit_chat = new ArrayList<>(); // To store the student who will chats with a
            for (int i = 1; i <= NumOfFriendsMeet; i++) {
                Student c = temp.get(r.nextInt(temp.size()));
                while (c.equals(a)) {
                    c = temp.get(r.nextInt(temp.size())); // To avoid propagating to same student again in multiple propagation
                }
                if (NumOfFriendsMeet == 1) {
                    print.add(c.getName() + ".");
                } else {
                    while (chit_chat.contains(c)) {
                        c = temp.get(r.nextInt(temp.size())); // To avoid meeting the same friends at the same time
                    }
                    if (i == NumOfFriendsMeet) {
                        print.add(c.getName() + "."); // The last friend he/she meet
                    } else {
                        print.add(c.getName() + ", ");
                    }
                }
                chit_chat.add(c);
            }
            print.add(""+ "\n");
            startChatting(a, chit_chat);
            talkGood_Bad(talked, a, chit_chat, this.messageToPropagate); // Now, a is talking good / bad about b to his/her friend(s) to chit_chat group
        } else {
            print.add(a.getName() + " : So sad! I have no more friends to propagate about " + talked.getName() + "\n");
        }
    }

    // "Talked" finally know he is being propagated
    public void getTruth() {
        if (isPropagate) {
            print.add("After a few days, " + talked.getName() + " is propagated to many students...."+ "\n");
            Student a = StudentList.studentList.get(r.nextInt(StudentList.studentList.size()));
            // To allow a stranger/someone has impression to him  to tell him
            while (talked.getFrenemy().contains(a) || talked.getEnemy().contains(a) || talked.getFriends().contains(a) || a.equals(talked)) {
                a = StudentList.studentList.get(r.nextInt(StudentList.studentList.size()));
            }
            print.add(a.getName() + " : Hi," + " I heard many people said: " + messageToPropagate+ "\n");
            if (good) {
                print.add(talked.getName() + " : " + "Thank you so much"+ "\n");
            } 
            else {
                print.add(talked.getName() + " : " + "Do you know who viral these?"+ "\n");
                print.add(a.getName() + " : " + "Not really"+ "\n");
                print.add("(In " + talked.getName() + "'s thought): " + "I should check out whether my friends start propagating these!"+ "\n");
                print.add("........................................................................"+ "\n");
                talked.getFrenemyList();
                talked.getEnemyList();
                print.add("........................................................................"+ "\n");
                negotiateFrenemy(talked.getFrenemy());
                // "talked" has negotiated with the frenemies, the frenemies are not frenemies anymore
                while(!talked.getFrenemy().isEmpty()){
                    talked.getFrenemy().remove(talked.getFrenemy().get(0));
                }
                print.add(""+ "\n");
                if (lie) {
                    negotiateEnemy(enemy);
                }
            }
        } else {
            print.add("The message is not propagated"+ "\n");
        }
    }

    // "talked" knows friends that are viraling the bad message
    public void negotiateFrenemy(ArrayList<Student> frenemy) {
        if (!frenemy.isEmpty()) {
            print.add("\nFinally, " + talked.getName() + " knows that " + storeName(frenemy) + " viraling his bad message..."+ "\n");
            print.add(talked.getName() + " : Why you want to propagate my bad message to other students? I think we are friends"+ "\n");
            print.add(storeName(frenemy) + ": Not sure, just for fun"+ "\n");
            print.add(talked.getName() + " : You should apologize!"+ "\n");
            for (int i = 0; i < frenemy.size(); i++) {
                if (apologize(frenemy.get(i))) {
                    print.add(frenemy.get(i).getName() + " : I am sorry because I didn't know the consequence of propagating this"+ "\n");
                    if (forgive(talked)) {
                        print.add(talked.getName() + ": Okay, it's fine. We're still friends now!"+ "\n");
                        talked.setReputation(frenemy.get(i), chekRepPoints(talked.getReputation().get(frenemy.get(i)) - 2)); // Due to this matter, (talked -> friend) -2
                    } 
                    else {
                        //talked choose not to forgive
                        print.add(talked.getName() + ": Okay, it's fine..."+ "\n");
                        talked.setReputation(frenemy.get(i), 1); // "talked" now leave a very bad impression to friend
                        talked.getFriends().remove(frenemy.get(i)); // "talked" do not want to befriend "friend" anymore since he choose not to forgive
                        //"friends" still treat "taledk" as friends
                    }
                } // Frenemy do not want to apologize
                else {
                    print.add(frenemy.get(i).getName() + " : Why so serious, it's just a joke only!"+ "\n");
                    print.add(talked.getName() + ": Ok, fine! Then we are not friends anymore"+ "\n");
                    print.add(frenemy.get(i).getName() + " : No problem!"+ "\n");
                    talked.getFriends().remove(frenemy.get(i)); // Now, talked and "friend" are not friends anymore
                    talked.setReputation(frenemy.get(i), -1); // Only enemy will have reputatiom (-1)
                    print.add("\nSince " + talked.getName() + " is hurt by " + frenemy.get(i).getName() + ", " + talked.getName() + " treat " + frenemy.get(i).getName() + " as enemy now\n"+ "\n");
                    talked.addEnemy(frenemy.get(i)); // But "friend" is not affected, just dont befriend "talked" only
                    frenemy.get(i).getFriends().remove(talked);
                    frenemy.get(i).setReputation(talked, 1);
                }
            }
        }
    }

    // "talked" knows "newFriend" purposely lie and destroy him
    public void negotiateEnemy(Student lie) {
        print.add("After that, " + talked.getName() + " knows that " + lie.getName() + " is the first one spreading the fake message"+ "\n");
        print.add(talked.getName() + " : Hey, how dare you purposely lie and destroy me?"+ "\n");
        print.add(lie.getName() + " : I just feel envious, that's why I spread it"+ "\n");
        print.add(talked.getName() + " : You should aplogize to me!"+ "\n");
        if (apologize(lie)) {
            print.add(lie.getName() + " : Sorry"+ "\n");
            print.add(talked.getName() + " : No next time please!"+ "\n");
        } 
        else {
            print.add(lie.getName() + " : I don't think I do anything wrong!"+ "\n");
            print.add(talked.getName() + " : You are so bad!"+ "\n");
            lie.getFriends().remove(talked);
            lie.addEnemy(talked); // Since "talked" know the truth and "lie" still dont willing to apologize, both are enemy
            lie.setReputation(talked, -1);
        }
        print.add("I will never forgive the one that purposely destroy my reputation"+ "\n");
        talked.setReputation(lie, -1);
        talked.getFriends().remove(lie);
    }

    public void startChatting(Student a, ArrayList<Student> b) {
        print.add(a.getName() + " : Hello, " + storeName(b)+ "\n");
        print.add(storeName(b) + ": Hi, " + a.getName()+ "\n");
        print.add(a.getName() + " : Do you free to sit down and chit-chat?"+ "\n");
        print.add(storeName(b) + ": Sure! "+ "\n");
    }

    public boolean forgive(Student talk) {
        print.add(talk.getName() + ", Do you want to forgive him/them?(Yes/No): "+ "\n");
        if (Math.random()> 0.5) {
            print.add("Yes"+ "\n");
            return true;
        } else {
            print.add("No"+ "\n");
            return false;
        }
    }

    public boolean apologize(Student viral) {
        print.add(viral.getName() + ", Do you want to apologize to " + talked.getName() + "?(Yes/No): "+ "\n");
        if (Math.random()> 0.5) {
            print.add("Yes"+ "\n");
            return true;
        } else {
            print.add("No"+ "\n");
            return false;
        }
    }

    /*
    @param a is the one who answer "b" lab question last time
    @param b is the one who ask "a" last time
    @param c is the list of student who are listening to b now
     */
    public void talkGood_Bad(Student a, Student b, ArrayList<Student> c, String message) {
        print.add(b.getName() + " : " + "Let me tell you something about " + a.getName()+ "\n");
        // 'a' only have strengths
        if (!store.contains(b)) {
            store.add(b); // To store the student who knows the good/bad about a student
        }
        if (b.equals(newFriend)) {
            if (allStrengths(a)) {
                if (shouldChooseToLie(b)) {
                    this.enemy = b;
                    this.lie = true;
                    // 'b' choose to purposely destroy a
                    a.addEnemy(b); // At this moment, 'b' still dont know who is the first one creating this rumour
                    this.badMessage = this.messageToPropagate = " actually, " + a.getName() + " is bad in programming and lazy, you should avoid meeting him!";
                } else {
                    // 'a' choose to viral 'b' goods
                    good = true;
                    this.messageToPropagate = " actually, " + a.getName() + " is not only good in programming but also productive in doing the assignment";
                }
            } // 'a' have only 1 strength
            else if (oneStrength(a)) {
                if (talkGoodOrBad(b, a)) {
                    if (a.isIsProgrammingExpert()) {
                        good = true;
                        this.messageToPropagate = " actually, " + a.getName() + " is really good in programming!";
                    } else if (a.getDivingRate() < 50) {
                        good = true;
                        this.messageToPropagate = " actually, " + a.getName() + " is productive in doing assignment!";
                    }
                } else {
                    if (!a.isIsProgrammingExpert()) {
                        // 'b' can choose to propagate 'a' good but he dont
                        a.addFrenemy(b);
                        this.badMessage = this.messageToPropagate = " actually, " + a.getName() + " is so bad in programming";
                    } else {
                        // 'b' can choose to propagate 'a' good but he dont
                        a.addFrenemy(b);
                        this.badMessage = this.messageToPropagate = " actually, " + a.getName() + " is a free-rider, just be aware of him!";
                    }
                }
            } // 'a' has only weakness
            else if (noStrengths(a)) {
                // 'b' purposely spread his weakness
                a.addFrenemy(b);
                this.badMessage = this.messageToPropagate = " actually, " + a.getName() + " is bad in programming and lazy, you should avoid meeting him!";
            }
        }

        print.add(b.getName() + " :" + messageToPropagate + "\n");
        print.add(storeName(c) + ": Thanks for telling these!"+ "\n");
        print.add(b.getName() + " : You're welcome!"+ "\n");
        print.add("\nIt is time to go to class now...."+ "\n");
        print.add(b.getName() + " : See you next time, Bye!"+ "\n");
        print.add(storeName(c) + ": Bye!\n"+ "\n");
        for (int i = 0; i < c.size(); i++) {
            store.add(c.get(i));
        }
        thinkYourself(good, c, a, b, messageToPropagate);
    }

    /*
    @param good_bad is true when say good things
    @param c is the friends of 'b' listening to him
    @param a is the students to be mentioned in the conversation
     */
    public void thinkYourself(boolean good_bad, ArrayList<Student> c, Student a, Student b, String message) {
        // if strengths/strength of 'a' is propagate
        if (good_bad) {
            for (int i = 0; i < c.size(); i++) {
                // If student in c knows a before
                if (c.get(i).inRepList(a)) {
                    int rep_points = c.get(i).getReputation().get(a) + (b.getReputation().get(a) / 2);
                    c.get(i).setReputation(a, chekRepPoints(rep_points));
                } // If student in c does not know a before
                else {
                    int rep_points = (b.getReputation().get(a)) / 2;
                    c.get(i).setReputation(a, chekRepPoints(rep_points));
                }
                print.add("(In " + c.get(i).getName() + " thought) : I have a good impression with " + a.getName()+ "\n");
                if (!shoudlContinuePropagating(c.get(i))) {
                                continue;
                            }
            }
        } // if bad of 'a' is propagated
        else {
            for (int i = 0; i < c.size(); i++) {
                // Coincidentally, student in c is friends of "b" as well as "a"
                if (c.get(i).getFriends().contains(a) && (oneStrength(a) || allStrengths(a))) {
                    if (shouldHelp(c.get(i))) {
                        if (allStrengths(a)) {
                            print.add("(In " + c.get(i).getName() + " 's thought): Why he want to purposely destroy " + a.getName()+". I have a bad impression with " + b.getName() + " now"+ "\n");
                            c.get(i).setReputation(b, 2);
                            this.messageToPropagate = traitsOfStudent(a);
                            good = true;
                            print.add("In the night..."+ "\n");
                            print.add(c.get(i).getName() + " messages the students that misunderstand " + a.getName() + " to clearify it..."+ "\n");
                            print.add(c.get(i).getName() + " to " + storeName(store, c.get(i)) + " : " + messageToPropagate + ". Please dont misunderstand " + a.getName() + "!\n"+ "\n");
                        } else {
                            this.messageToPropagate = traitsOfStudent(a); // Didnt influence reputation to b because "a" really got weakness
                            good = true;
                            print.add("In the night..."+ "\n");
                            print.add(c.get(i).getName() + " messages the students that know " + a.getName() + " 's bad"+ "\n");
                            print.add(c.get(i).getName() + " to " + storeName(store, c.get(i)) +" : "+" Although " + a.getName() + " " + getBad(a) + " but " + messageToPropagate+"\n"+ "\n");
                        }
                        // Since student in c is "a" friends, his rep point to "a" remains unchanged because he knows "a" actual attitude, just the case that he wants to help "a"/not
                    } 
                    else {
                        messageToPropagate = badMessage; // If more than 1 friend in friendlist of "b" = friend of "a", If other friend choose not to help, he will spread bad message 
                        good = false;
                    }
                } // If student in c is have some impression/reputation to a before but they are not friend
                else if (c.get(i).inRepList(a)) {
                    int rep_points = c.get(i).getReputation().get(a) - b.getReputation().get(a);
                    c.get(i).setReputation(a, chekRepPoints(rep_points));
                    print.add("(In " + c.get(i).getName() + " thought) : I have a bad impression with " + a.getName()+ "\n");
                } // If student in c does not know a before
                else {
                    c.get(i).setReputation(a, 1); // After knowing the worse attitude of "a", he/she left a bad impression to a
                    print.add("(In " + c.get(i).getName() + " thought) : I have a bad impression with " + a.getName()+ "\n");
                }
                if (!shoudlContinuePropagating(c.get(i))) {
                                continue;
                            }
            }
        }
    }

    /*
    @param 'a' ask question
    @param 'b' answer question
     */
    public boolean talkGoodOrBad(Student a, Student b) {
        print.add(a.getName() + ", Do you want to viral " + b.getName() + " (strength/weakness) "+ "\n");
        if (Math.random()>0.5) {
            print.add("strength"+ "\n");
            return true;
        } else {
            print.add("weakness"+ "\n");
            return false;
        }
    }

    /*
    @param get the strengths of b
     */
    public String traitsOfStudent(Student b) {
        String message = "";
        if (allStrengths(b)) {
            message = " actually, " + b.getName() + " is not only good in programming but also productive in doing the assignment";
        } // 'a' have only 1 strength
        else if (oneStrength(b)) {
            if (b.isIsProgrammingExpert()) {
                message = " actually, " + b.getName() + " is really good in programming!";
            } else if (b.getDivingRate() < 50) {
                message = " actually, " + b.getName() + " is productive in doing assignment!";
            }
        } // 'a' has only weakness
        return message;
    }

    public boolean shouldChooseToLie(Student a) {
        print.add("What you want to choose?\n1. Viral " + talked.getName() + " strengths\n2. Make black white"+ "\n");
        if (Math.random()>0.5) {
            return false;
        } else {
            print.add("(Due to " + a.getName() + " envy of strengths of " + talked.getName() + ", he decides to destroy him)"+ "\n");
            return true;
        }
    }

    /*
    @param c is the friends of "talked"
     */
    public boolean shouldHelp(Student c) {
        print.add(c.getName() + ", Do you want to help " + talked.getName() + "?(Yes/No):"+ "\n");

        if (Math.random()>0.5) {
            print.add("Yes"+ "\n");
            return true;
        } else {
            print.add("No"+ "\n");
            return false;
        }
    }

    /*
    @param 'b' is the student to be propagated
    @return true if all strength and false if have weakness
     */
    public boolean allStrengths(Student b) {
        if (b.isIsProgrammingExpert() && b.getDivingRate() < 50) {
            return true;
        } else {
            return false;
        }
    }

    /*
    @param 'b' is the student to be propagated
    @return true if all weakness and false if got strength
     */
    public boolean noStrengths(Student b) {
        if (!b.isIsProgrammingExpert() && b.getDivingRate() > 50) {
            return true;
        } else {
            return false;
        }
    }

    /*
    @param 'b' is the student to be propagated
    @return true if have only 1 strength and false if no any strength
     */
    public boolean oneStrength(Student b) {
        if (b.isIsProgrammingExpert()) {
            return true;
        } else if (b.getDivingRate() < 50) {
            return true;
        } else {
            return false; // Not a expert and also a free-rider
        }
    }

    public String getBad(Student a) {
        String message = "";
        if (!a.isIsProgrammingExpert()) {
            message = "not good in programming";
        } else if (a.getDivingRate() >= 50) {
            message = "quite lazy in doing the assignment";
        }
        return message;
    }

    /*
    @param : a is the student that will decide whether to propagate about talked
     */
    public boolean shoudlContinuePropagating(Student a) {
        print.add(a.getName() + ", do you want to propagate the messages?(Yes/No): "+ "\n");
        boolean canViral = false;
        if (Math.random()>0.5) {
            print.add("Yes"+ "\n");
            for (int i = 0; i < a.getFriends().size(); i++) {
                if (store.contains(a.getFriends().get(i)) || a.getFriends().get(i).equals(talked)) {
                    continue;
                }
                isPropagate = true; // If he still have friends who are not involved in this propagation
                canViral = true;
                break;
            }
            // a is friend of 'talked' and decide to viral his bad
            if (talked.getFriends().contains(a) && !good && canViral) {
                talked.addFrenemy(a); // As a friend, you can stop viraling the bad of 'talked' even 'talked' has no any strengths
            }
            decideChatting(a, a.getFriends());
            return true;
        } else {
            print.add("No"+ "\n");
            print.add(a.getName() + ", You are so kind that you didn't talk about other people!"+ "\n");
            return false;
        }
    }

    public int chekRepPoints(int rep_points) {
        if (rep_points > 10) {
            rep_points = 10; // The highest rep points == 10
        } else if (rep_points < 1) {
            rep_points = 1; // The lowest rep_points == 1
        }
        return rep_points;
    }

    // To make a list of students's names into a string
    public String storeName(ArrayList<Student> a) {
        if (a.isEmpty()) {
            return null;
        }
        StringBuilder c = new StringBuilder();
        for (int i = 0; i < a.size(); i++) {
            if (i == a.size() - 1) {
                c.append(a.get(i).getName() + " ");
            } else {
                c.append(a.get(i).getName() + ",");
            }
        }
        return c.toString();
    }

    //Overloading ( To print the list with the exception of one student
    public String storeName(ArrayList<Student> a, Student b) {
        if (a.isEmpty()) {
            return null;
        }
        StringBuilder c = new StringBuilder();
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getName().equals(b.getName())) {
                continue;
            }
            if (i == a.size() - 1) {
                c.append(a.get(i).getName() + " ");
            } else {
                c.append(a.get(i).getName() + ",");
            }
        }
        return c.toString();
    }
}
