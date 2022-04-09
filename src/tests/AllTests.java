package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Tests_DAO_Artists.class, Tests_DAO_Musics.class, Tests_DAO_Users.class, Tests_DAO_Playlists.class})
public class AllTests {

}
