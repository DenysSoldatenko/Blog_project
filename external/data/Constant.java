package com.example.project.data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Constant class contains constant values and utility methods for the blog project.
 */
public class Constant {
  public static final String DUMMY_CONTENT_TEXT = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc, quis gravida magna mi a libero. Fusce vulputate eleifend sapien. Vestibulum purus quam, scelerisque ut, mollis sed, nonummy id, metus. Nullam accumsan lorem in dui. Cras ultricies mi eu turpis hendrerit fringilla. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In ac dui quis mi consectetuer lacinia. Nam pretium turpis et arcu. Duis arcu tortor, suscipit eget, imperdiet nec, imperdiet iaculis, ipsum. Sed aliquam ultrices mauris. Integer ante arcu, accumsan a, consectetuer eget, posuere ut, mauris. Praesent adipiscing. Phasellus ullamcorper ipsum rutrum nunc. Nunc nonummy metus. Vestibulum volutpat pretium libero. Cras id dui. Aenean ut eros et nisl sagittis vestibulum. Nullam nulla eros, ultricies sit amet, nonummy id, imperdiet feugiat, pede. Sed lectus. Donec mollis hendrerit risus. Phasellus nec sem in justo pellentesque facilisis. Etiam imperdiet imperdiet orci. Nunc nec neque. Phasellus leo dolor, tempus non, auctor et, hendrerit quis, nisi. Curabitur ligula sapien, tincidunt non, euismod vitae, posuere imperdiet, leo. Maecenas malesuada. Praesent congue erat at massa. Sed cursus turpis vitae tortor. Donec posuere vulputate arcu. Phasellus accumsan cursus velit. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Sed aliquam, nisi quis porttitor congue, elit erat euismod orci, ac placerat dolor lectus quis orci. Phasellus consectetuer vestibulum elit. Aenean tellus metus, bibendum sed, posuere ac, mattis non, nunc. Vestibulum fringilla pede sit amet augue. In turpis. Pellentesque posuere. Praesent turpis. Aenean posuere, tortor sed cursus feugiat, nunc augue blandit nunc, eu sollicitudin urna dolor sagittis lacus. Donec elit libero, sodales nec, volutpat a, suscipit non, turpis. Nullam sagittis. Suspendisse pulvinar, augue ac venenatis condimentum, sem libero volutpat nibh, nec pellentesque velit pede quis nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Fusce id purus. Ut varius tincidunt libero. Phasellus dolor. Maecenas vestibulum mollis diam. Pellentesque ut neque. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. In dui magna, posuere eget, vestibulum et, tempor auctor, justo. In ac felis quis tortor malesuada pretium. Pellentesque auctor neque nec urna. Proin sapien ipsum, porta a, auctor quis, euismod ut, mi. Aenean viverra rhoncus pede. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Ut non enim eleifend felis pretium feugiat. Vivamus quis mi. Phasellus a est. Phasellus magna. In hac habitasse platea dictumst. Curabitur at lacus ac velit ornare lobortis. Curabitur a felis in nunc fringilla tristique. Morbi mattis ullamcorper velit. Phasellus gravida semper nisi. Nullam vel sem. Pellentesque libero tortor, tincidunt et, tincidunt eget, semper nec, quam. Sed hendrerit. Morbi ac felis. Nunc egestas, augue at pellentesque laoreet, felis eros vehicula leo, at malesuada velit leo quis pede. Donec interdum, metus et hendrerit aliquet, dolor diam sagittis ligula, eget egestas libero turpis vel mi. Nunc nulla. Fusce risus nisl, viverra et, tempor et, pretium in, sapien. Donec venenatis vulputate lorem. Morbi nec metus. Phasellus blandit leo ut odio. Maecenas ullamcorper, dui et placerat feugiat, eros pede varius nisi, condimentum viverra felis nunc et lorem. Sed magna purus, fermentum eu, tincidunt eu, varius ut, felis. In auctor lobortis lacus. Quisque libero metus, condimentum nec, tempor a, commodo mollis, magna. Vestibulum ullamcorper mauris at ligula. Fusce fermentum. Nullam cursus lacinia erat. Praesent blandit laoreet nibh. Fusce convallis metus id felis luctus adipiscing. Pellentesque egestas, neque sit amet convallis pulvinar, justo nulla eleifend augue, ac auctor orci leo non est. Quisque id mi. Ut tincidunt tincidunt erat. Etiam feugiat lorem non metus. Vestibulum dapibus nunc ac augue. Curabitur vestibulum aliquam leo. Praesent egestas neque eu enim. In hac habitasse platea dictumst. Fusce a quam. Etiam ut purus mattis mauris sodales aliquam. Curabitur nisi. Quisque malesuada placerat nisl. Nam ipsum risus, rutrum vitae, vestibulum eu, molestie vel, lacus. Sed augue ipsum, egestas nec, vestibulum et, malesuada adipiscing, dui. Vestibulum facilisis, purus nec pulvinar iaculis, ligula mi congue nunc, vitae euismod ligula urna in dolor. Mauris sollicitudin fermentum libero. Praesent nonummy mi in odio. Nunc interdum lacus sit amet orci. Vestibulum rutrum, mi nec elementum vehicula, eros quam gravida nisl, id fringilla neque ante vel mi. Morbi mollis tellus ac sapien. Phasellus volutpat, metus eget egestas mollis, lacus lacus blandit dui, id egestas quam mauris ut lacus. Fusce vel dui. Sed in libero ut nibh placerat accumsan. Proin faucibus arcu quis ante. In consectetuer turpis ut velit. Nulla sit amet est. Praesent metus tellus, elementum eu, semper a, adipiscing nec, purus. Cras risus ipsum, faucibus ut, ullamcorper id, varius ac, leo. Suspendisse feugiat. Suspendisse enim turpis, dictum sed, iaculis a, condimentum nec, nisi. Praesent nec nisl a purus blandit viverra. Praesent ac massa at ligula laoreet iaculis. Nulla neque dolor, sagittis eget, iaculis quis, molestie non, velit. Mauris turpis nunc, blandit et, volutpat molestie, porta ut, ligula. Fusce pharetra convallis urna. Quisque ut nisi. Donec mi odio, faucibus at, scelerisque quis, convallis in, nisi. ";

  public static final String NAMES_LIST = "James,John,Robert,Michael,William,David,Richard,Charles,Joseph,Thomas,Christopher,Daniel,Paul,Mark,Donald,George,Kenneth,Steven,Edward,Brian,Ronald,Anthony,Kevin,Jason,Matthew,Gary,Timothy,Jose,Larry,Jeffrey,Frank,Scott,Eric,Stephen,Andrew,Raymond,Gregory,Joshua,Jerry,Dennis,Walter,Patrick,Peter,Harold,Douglas,Henry,Carl,Arthur,Ryan,Roger,Joe,Juan,Jack,Albert,Jonathan,Justin,Terry,Gerald,Keith,Samuel,Willie,Ralph,Lawrence,Nicholas,Roy,Benjamin,Bruce,Brandon,Adam,Harry,Fred,Wayne,Billy,Steve,Louis,Jeremy,Aaron,Randy,Howard,Eugene,Carlos,Russell,Bobby,Victor,Martin,Ernest,Phillip,Todd,Jesse,Craig,Alan,Shawn,Clarence,Sean,Philip,Chris,Johnny,Earl,Jimmy,Antonio,Danny,Bryan,Tony,Luis,Mike,Stanley,Leonard,Nathan,Dale,Manuel,Rodney,Curtis,Norman,Allen,Marvin,Vincent,Glenn,Jeffery,Travis,Jeff,Chad,Jacob,Lee,Melvin,Alfred,Kyle,Francis,Bradley,Jesus,Herbert,Frederick,Ray,Joel,Edwin,Don,Eddie,Ricky,Troy,Randall,Barry,Alexander,Bernard,Mario,Leroy,Francisco,Marcus,Micheal,Theodore,Clifford,Miguel,Oscar,Jay,Jim,Tom,Calvin,Alex,Jon,Ronnie,Bill,Lloyd,Tommy,Leon,Derek,Warren,Darrell,Jerome,Floyd,Leo,Alvin,Tim,Wesley,Gordon,Dean,Greg,Jorge,Dustin,Pedro,Derrick,Dan,Lewis,Zachary,Corey,Herman,Maurice,Vernon,Roberto,Clyde,Glen,Hector,Shane,Ricardo,Sam,Rick,Lester,Brent,Ramon,Charlie,Tyler,Gilbert,Gene,Marc,Reginald,Ruben,Brett,Angel,Nathaniel,Rafael,Leslie,Edgar,Milton,Raul,Ben,Chester,Cecil,Duane,Franklin,Andre,Elmer,Brad,Gabriel,Ron,Mitchell,Roland,Arnold,Harvey,Jared,Adrian,Karl,Cory,Claude,Erik,Darryl,Jamie,Neil,Jessie,Christian,Javier,Fernando,Clinton,Ted,Mathew,Tyrone,Darren,Lonnie,Lance,Cody,Julio,Kelly,Kurt,Allan,Nelson,Guy,Clayton,Hugh,Max,Dwayne,Dwight,Armando,Felix,Jimmie,Everett,Jordan,Ian,Wallace,Ken,Bob,Jaime,Casey,Alfredo,Alberto,Dave,Ivan,Johnnie,Sidney,Byron,Julian,Isaac,Morris,Clifton,Willard,Daryl,Ross,Virgil,Andy,Marshall,Salvador,Perry,Kirk,Sergio,Marion,Tracy,Seth,Kent,Terrance,Rene,Eduardo,Terrence,Enrique,Freddie,Wade,Austin,Stuart,Fredrick,Arturo,Alejandro,Jackie,Joey,Nick,Luther,Wendell,Jeremiah,Evan,Julius,Dana,Donnie,Otis,Shannon,Trevor,Oliver,Luke,Homer,Gerard,Doug,Kenny,Hubert,Angelo,Shaun,Lyle,Matt,Lynn,Alfonso,Orlando,Rex,Carlton,Ernesto,Cameron,Neal,Pablo,Lorenzo,Omar,Wilbur,Blake,Grant,Horace,Roderick,Kerry,Abraham,Willis,Rickey,Jean,Ira,Andres,Cesar,Johnathan,Malcolm,Rudolph,Damon,Kelvin,Rudy,Preston,Alton,Archie,Marco,Wm,Pete,Randolph,Garry,Geoffrey,Jonathon,Felipe,Bennie,Gerardo,Ed,Dominic,Robin,Loren,Delbert,Colin,Guillermo,Earnest,Lucas,Benny,Noel,Spencer,Rodolfo,Myron,Edmund,Garrett,Salvatore,Cedric,Lowell,Gregg,Sherman,Wilson,Devin,Sylvester,Kim,Roosevelt,Israel,Jermaine,Forrest,Wilbert,Leland,Simon,Guadalupe,Clark,Irving,Carroll,Bryant,Owen,Rufus,Woodrow,Sammy,Kristopher,Mack,Levi,Marcos,Gustavo,Jake,Lionel,Marty,Taylor,Ellis,Dallas,Gilberto,Clint,Nicolas,Laurence,Ismael,Orville,Drew,Jody,Ervin,Dewey,Al,Wilfred,Josh,Hugo,Ignacio,Caleb,Tomas,Sheldon,Erick,Frankie,Stewart,Doyle,Darrel,Rogelio,Terence,Santiago,Alonzo,Elias,Bert,Elbert,Ramiro,Conrad,Pat,Noah,Grady,Phil,Cornelius,Lamar,Rolando,Clay,Percy,Dexter,Bradford,Merle,Darin,Amos,Terrell,Moses,Irvin,Saul,Roman,Darnell,Randal,Tommie,Timmy,Darrin,Winston,Brendan,Toby,Van,Abel,Dominick,Boyd,Courtney,Jan,Emilio,Elijah,Cary,Domingo,Santos,Aubrey,Emmett,Marlon,Emanuel,Jerald,Edmond,Emil,Dewayne,Will,Otto,Teddy,Reynaldo,Bret,Morgan,Jess,Trent,Humberto,Emmanuel,Stephan,Louie,Vicente,Lamont,Stacy,Garland,Miles,Micah,Efrain,Billie,Logan,Heath,Rodger,Harley,Demetrius,Ethan,Eldon,Rocky,Pierre,Junior,Freddy,Eli,Bryce,Antoine,Robbie,Kendall,Royce,Sterling,Mickey,Chase,Grover,Elton,Cleveland,Dylan,Chuck,Damian,Reuben,Stan,August,Leonardo,Jasper,Russel,Erwin,Benito,Hans,Monte,Blaine,Ernie,Curt,Quentin,Agustin,Murray,Jamal,Devon,Adolfo,Harrison,Tyson,Burton,Brady,Elliott,Wilfredo,Bart,Jarrod,Vance,Denis,Damien,Joaquin,Harlan,Desmond,Elliot,Darwin,Ashley,Gregorio,Buddy,Xavier,Kermit,Roscoe,Esteban,Anton,Solomon,Scotty,Norbert,Elvin,Williams,Nolan,Carey,Rod,Quinton,Hal,Brain,Rob,Elwood,Kendrick,Darius,Moises,Son,Marlin,Fidel,Thaddeus,Cliff,Marcel,Ali,Jackson,Raphael,Bryon,Armand,Alvaro,Jeffry,Dane,Joesph,Thurman,Ned,Sammie,Rusty,Michel,Monty,Rory,Fabian,Reggie,Mason,Graham,Kris,Isaiah,Vaughn,Gus,Avery,Loyd,Diego,Alexis,Adolph,Norris,Millard,Rocco,Gonzalo,Derick,Rodrigo,Gerry,Stacey,Carmen,Wiley,Rigoberto,Alphonso,Ty,Shelby,Rickie,Noe,Vern,Bobbie,Reed,Jefferson,Elvis,Bernardo,Mauricio,Hiram,Donovan,Basil,Riley,Ollie,Nickolas,Maynard,Scot,Vince,Quincy,Eddy,Sebastian,Federico,Ulysses,Heriberto,Donnell,Cole,Denny,Davis,Gavin,Emery,Ward,Romeo,Jayson,Dion,Dante,Clement,Coy,Odell,Maxwell,Jarvis,Bruno,Issac,Mary,Dudley";

  public static final int CATEGORY_SIZE = 10;

  public static final int MIN_ARTICLES_PER_CATEGORY = 1;

  public static final int MAX_ARTICLES_PER_CATEGORY = 30;

  public static final int START_ARTICLE_ID = 200;

  public static final String DEST_MEDIA
      = "D:\\Projects\\Blog_project\\src\\main\\webapp\\media";

  public static final String JDBC_URL = "jdbc:postgresql://localhost:5432/blog";

  public static final String JDBC_USERNAME = "postgres";

  public static final String JDBC_PASSWORD = "postgres";

  public static final String[] IMG_THEME = {
    "arch",
    "nature",
    "tech",
    "animals"
  };

  public static final String[] NAMES = NAMES_LIST.split(",");

  public static final List<String> SENTENCES = new ArrayList<>();

  public static final List<String> WORDS = new ArrayList<>();

  public static final Random random = new Random();

  public static final long MILLISECONDS_IN_DAY = 24 * 60 * 60 * 1000;

  public static int idArticle = START_ARTICLE_ID;

  public static int articleCount = 0;

  public static int accountCount = 0;

  /**
   * Generates a random created timestamp.
   *
   * @return a generated Timestamp representing the creation time
   */
  public static Timestamp generateCreatedTimestamp() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.HOUR_OF_DAY, -Constant.random.nextInt(24));
    calendar.add(Calendar.MINUTE, -Constant.random.nextInt(60));
    calendar.add(Calendar.DAY_OF_MONTH, -Constant.random.nextInt(30) - Constant.accountCount);
    calendar.add(Calendar.MONTH, -Constant.random.nextInt(12));
    calendar.add(Calendar.YEAR, -Constant.random.nextInt(1));
    return new Timestamp(calendar.getTimeInMillis());
  }

  /**
   * Downloads an image from the internet and saves it as a file.
   *
   * @param url      the URL of the image to download
   * @param fileName the name of the file to save the image as
   * @param logger   the Logger for logging messages
   * @throws IOException if an I/O error occurs during the download or file writing
   */
  public static void downloadImageFromInternet(String url, String fileName,
                                               Logger logger) throws IOException {
    File file = new File(fileName);
    if (!file.getParentFile().exists()) {
      boolean dirsCreated = file.getParentFile().mkdirs();
      if (!dirsCreated) {
        logger.log(Level.WARNING, "Failed to create directories for file: "
                + file.getAbsolutePath());
        return;
      }
    }
    try (InputStream in = new URL(url).openStream()) {
      Files.copy(in, Paths.get(fileName));
    }
  }
}
