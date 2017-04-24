# NameGame
NameGame Implementation for WillowTree

### Overall:
This exercise was a lot of fun. It hits alot of Android concepts to test on one's skill. With that being said, I have also come to learn what I don't know. 

### Challenges I Faced:
This includes Retrofit and Dagger, this project was the 1st time I encountered these technologies. Unfortunately due to time constraints, I was able to do some reading on the technologies, however, I was not able to retain in this shortspan to reuse the skeleton project :/ . I simply thought I had to @Inject ProfilesRepository in the fragment to access the data. The callback kept hitting negative, and was unable to figure out if there was an issue with the Retrofit setup including the models or if I was missing a module to setup the ProfilesRepository Bean.

### What I Learned:
After sometime, I took a route of re-inventing the wheel. I created what I believe to be a somewhat scalable application. Starting from scratch provided me another great opportunity. My applications on the PlayStore function actually quite well, but they are very difficult to manage (not scalable at all). I wanted to make sure I didn't provide 'hackish' code so decded to give MVP a wirl (MVP was brought up during the technical interview, and saw it was favored online). I'm decently acquainted with MVC concepts, so the transition was okay. I actually thought the structure turned out pretty good. There were a couple of circular dependencies, that at the time I couldn't figure out how to mitigate, but for the most part it was pretty clear in what was the view and what was the presenter. It put in perspective how much work my techlead has to do at my current position, setting up enterprise grade framework, and I get to skip all the guess work and just program to the interface.

Another new technology I got to use was Picasso. That library is sweet! Lot's of options, and very fast download speeds. Alot cleaner than the traditional Asynctask Bitmap decifer, which I'm used to using. This was the 2nd project I've used Butterknife, really starting to enjoy this technology and understand the benefits that come with it. 

### The Results:
- [x] Stat tracking. How many correct / incorrect attempts did the user make? How long does it take on average for a person to identify the subject?
- [ ] Spruce up transitions and image loading. Don't let images pop in and show the user that loading is happening
Game modes:
- [x] Mat(t) Mode. Roughly 90% of our co-workers are named Mat(t), so add a challenge mode where you only present the users with A Mat(t).
- [x] Reverse mode: Show one face with 5 names. Ask the user to identify the correct name.
- [x] Hint mode. As people wait, faces disappear until only the correct one is left.
- [ ] Insert your own idea here! (If you guys don't have time to checkout the repository for awhile, I would really like to throw in a flashcard/practice mode)



By the way, I loved the util package, it was definitely nice to reuse that arsenal to set up some nice logical and view flows.
Thanks for the project :) I learned alot and had fun.
