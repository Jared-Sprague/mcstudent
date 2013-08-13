mcstudent
=========

Minecraft Student Mod makes minecraft players better students by asking practice math questions during play.

Installation
-------------------
1. Install Minecraft Forge.
2. Download Davidee's GUI Library (http://tinyurl.com/llhuhm8) and place it in the mods folder.
3. Place this mod in the mods folder. 
4. Run the game once to generate the config file.
5. Edit the config file: .minecraft/config/MCSTUDENT.cfg to inclue the following:
```cfg
student_category {
    # How many minutes between questions. Default 3.
    question_minutes=3

    # What grade level math questions to ask. Possible 1-4. Default 1.
    student_grade=1  
}
```
