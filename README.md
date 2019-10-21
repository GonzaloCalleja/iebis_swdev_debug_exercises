# iebis_swdev_debugging
Source code to test debugging

## Instructions
First, **Fork** this project.

There are three exercises splitted in three branches of this repository. You must switch branches to checkout the code of each exercise.
Then, find the bugs that appear in each branch.
Fix the bugs if you can and answer to the questions proposed below.
Commit the code before checking out a different branch to avoid loosing the fixes that you have made to the code.

Once that you are done fixing bugs, **to score you must**:
1. Switch to the master branch.
2. Type below in this README.md file the answer to each question and paste some code that you have used to solve them.
3. Commit the changes
4. Push to your GitHub repository
5. **Finally place a Pull Request so I can see your proposed answers**


## Exercises
### Exercise 1
In this code there is a class called WordAnalyzer that contains several methods that analyzes some characteristics of the word passed as argument when the object WordAnalyzer is created.

For some reason, the methods are not working properly, sometimes they return the correct value and others don't. You need to answer the next questions.

#### Why the method _firstMultipleCharacter_ is returning "c" for the word _comprehensive_, when the correct answer should be "e"?
In the find method, the position has to be increased by 1, so that it doesn't compare every letter with itself.
```java
private int find(char c, int pos)
    {
        for (int i = pos+1; i < word.length(); i++) //pos +1
        {
            if (word.charAt(i) == c)
            {
                return i;
            }
        }
        return -1;
    }
```
#### Why the method _firstRepeatedCharacter_ is throwing an exception?
Because since arrays start counting at 0, you have to subtract 1 from the array length.
```java
for (int i = 0; i < word.length() -1; i++) // you have to subtract 1 from the array length or you will get an exception
        {
            char ch = word.charAt(i);
            if (ch == word.charAt(i + 1))
                return ch;
        }
```
#### Why the method _countGroupsRepeatedCharacters_ returns 3 in one case when it should be 4?
i has to start at 0, and you have to check if it is the first letter of the array.
```java
public int countGroupsRepeatedCharacters()
    {
        int c = 0;
        for (int i = 0; i < word.length() - 1; i++) // the array has to start at 0 not at 1
        {
            if (word.charAt(i) == word.charAt(i + 1)) // found a repetition
            {
                if(i!=0) { // checking if its the first letter
                    if (word.charAt(i - 1) != word.charAt(i)) {  // it't the start
                        c++;
                    }
                } else{
                    c++;
                }
            }
        }
        return c;
    }
```


**Strategy**: Place breakpoints before the methods are executed, step into them and see what happens.


### Exercise 2
In this code we are placing mines in a board game where we have several spaces that can be mined. 
The boards can contain _Element_ objects, and since _Space_ and _Mine_ inherits from _Element_, the boards can contain this kind as well.

We have two boards of different size and place a different number of mines on each one. But in the second case it takes longer to place all the mines.

#### Why placing less bombs takes longer in the second case?
Because the amount of bombs represents a larger portion of the second board, and since the bombs are placed randomly, the free spaces in the second board run out earlier and the program is required to find another random position where to place the bomb.
#### Knowing that usually there are going to be more bombs than spaces in the final boards, how would you change the method _minningTheBoard_ to be more efficient?
The solution to this problem is to start with a board full of mines, and then assign random spaces to the board instead of random mines. This is because the number of mines is far larger than the number of spaces.

So the code would look like this:
```java
public static void settingTheBoard(int size) {
        // Setting the board
        myBoard = new HashMap<>();

        for(int i = 0; i < size; i++) {
            myBoard.put(i, new Space());
        }
    }

    public static void minningTheBoard(int numberMines) {
        Random random = new Random();
        while (numberMines > 0) {
            Integer trial = new Integer(random.nextInt(myBoard.size()));

            if (myBoard.get(trial) instanceof Space) {
                myBoard.put(trial, new Mine());
                numberMines--;
            }
        }
    }
```

**Strategy**: Understand well what the code does. Use conditionals breakpoints.


### Exercise 3
In this case this code looks really simple. When the "d" reaches the value 1.0, the program should end, but it never does.

#### Why does not appear a message indicating that "d is 1"?
#### How will you fix it?
