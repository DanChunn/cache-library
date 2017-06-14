package tests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	ProgramTestLRU.class,
	ProgramTestMRU.class,
	ProgramTestIllegalArgs.class
})
public class ProgramTestSuite {

}
