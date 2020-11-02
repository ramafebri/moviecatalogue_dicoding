package com.example.moviecatalogue.utils

import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.MoviesEntity
import com.example.moviecatalogue.data.TvShowsEntity

object DataDummy {
    fun generateMovies(): List<MoviesEntity> {

        val movies = ArrayList<MoviesEntity>()

        movies.add(MoviesEntity(
            "1",
            "A Star Is Born",
            "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            "",
            "2018",
            "Bradley Cooper",
            R.drawable.poster_a_start_is_born,
        ))
        movies.add(MoviesEntity(
            "2",
            "Alita: Battle Angel",
            "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
            "An angel falls. A warrior rises.",
            "2019",
            "Robert Rodriguez",
            R.drawable.poster_alita,
        ))
        movies.add(MoviesEntity(
            "3",
            "Aquaman",
            "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
            "Home Is Calling",
            "2018",
            "James Wan",
            R.drawable.poster_aquaman,
        ))
        movies.add(MoviesEntity(
            "4",
            "Bohemian Rhapsody",
            "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
            "Fearless lives forever",
            "2018",
            "Anthony McCarten",
            R.drawable.poster_bohemian,
        ))
        movies.add(MoviesEntity(
            "5",
            "Cold Pursuit",
            "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
            "Meet Nels Coxman. Citizen of the Year.",
            "2019",
            "Hans Petter Moland",
            R.drawable.poster_cold_persuit,
        ))
        movies.add(MoviesEntity(
            "6",
            "Creed",
            "The former World Heavyweight Champion Rocky Balboa serves as a trainer and mentor to Adonis Johnson, the son of his late friend and former rival Apollo Creed.",
            "Your legacy is more than a name",
            "2015",
            "Ryan Coogler",
            R.drawable.poster_creed,
        ))
        movies.add(MoviesEntity(
            "7",
            "Fantastic Beasts: The Crimes of Grindelwald",
            "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
            "Fate of One. Future of All.",
            "2018",
            "David Yates",
            R.drawable.poster_alita,
        ))
        movies.add(MoviesEntity(
            "8",
            "Glass",
            "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
            "You Cannot Contain What You Are",
            "2019",
            "M. Night Shyamalan",
            R.drawable.poster_glass,
        ))
        movies.add(MoviesEntity(
            "9",
            "How to Train Your Dragon",
            "As the son of a Viking leader on the cusp of manhood, shy Hiccup Horrendous Haddock III faces a rite of passage: he must kill a dragon to prove his warrior mettle. But after downing a feared dragon, he realizes that he no longer wants to destroy it, and instead befriends the beast – which he names Toothless – much to the chagrin of his warrior father",
            "One adventure will change two worlds",
            "2010",
            "Dean DeBlois",
            R.drawable.poster_how_to_train,
        ))
        movies.add(MoviesEntity(
            "10",
            "Avengers: Infinity War",
            "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
            "An entire universe. Once and for all.",
            "2018",
            "Joe Russo",
            R.drawable.poster_infinity_war,
        ))

        return movies
    }

    fun generateTvShows(): List<TvShowsEntity> {

        val tvShows = ArrayList<TvShowsEntity>()

        tvShows.add(TvShowsEntity(
            "1",
            "Arrow",
            "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
            "Heroes fall. Legends rise.",
            "2012",
            "Greg Berlanti",
            R.drawable.poster_arrow,
        ))
        tvShows.add(TvShowsEntity(
            "2",
            "Doom Patrol",
            "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
            "",
            "2019",
            "Jeremy Carver",
            R.drawable.poster_doom_patrol,
        ))
        tvShows.add(TvShowsEntity(
            "3",
            "Dragon Ball",
            "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku's home. Together, they set off to find all seven and to grant her wish.",
            "",
            "1986",
            "Akira Toriyama",
            R.drawable.poster_dragon_ball,
        ))
        tvShows.add(TvShowsEntity(
            "4",
            "Fairy Tail",
            "Natsu Dragneel and his friends travel to the island Kingdom of Stella, where they will reveal dark secrets, fight the new enemies and once again save the world from destruction.",
            "",
            "2017",
            "Tatsuma Minamikawa",
            R.drawable.poster_fairytail,
        ))
        tvShows.add(TvShowsEntity(
            "5",
            "Family Guy",
            "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
            "Parental Discretion Advised, that's how you know it's good",
            "1999",
            "Seth MacFarlane",
            R.drawable.poster_family_guy,
        ))
        tvShows.add(TvShowsEntity(
            "6",
            "The Flash",
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            "The fastest man alive.",
            "2014",
            "Greg Berlanti",
            R.drawable.poster_flash,
        ))
        tvShows.add(TvShowsEntity(
            "7",
            "Naruto Shippūden",
            "Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.",
            "",
            "2007",
            "Masashi Kisimoto",
            R.drawable.poster_naruto_shipudden,
        ))
        tvShows.add(TvShowsEntity(
            "8",
            "Gotham",
            "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
            "Before Batman, there was Gotham.",
            "2014",
            "Bruno Heller",
            R.drawable.poster_gotham,
        ))
        tvShows.add(TvShowsEntity(
            "9",
            "Grey's Anatomy",
            "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
            "The life you save may be your own.",
            "2005",
            "Shonda Rhimes",
            R.drawable.poster_grey_anatomy,
        ))
        tvShows.add(TvShowsEntity(
            "10",
            "Hanna",
            "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
            "",
            "2019",
            "David Farr",
            R.drawable.poster_hanna,
        ))

        return tvShows
    }
}