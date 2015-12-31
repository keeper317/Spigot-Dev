package mediaapps.SCB.managers;
import mediaapps.SCB.BlazeClass;
import mediaapps.SCB.CactusClass;
import mediaapps.SCB.CreeperClass;
import mediaapps.SCB.EndermanClass;
import mediaapps.SCB.SCB;
import mediaapps.SCB.SkeletonClass;
import mediaapps.SCB.SpiderClass;
import mediaapps.SCB.WitherClass;
import mediaapps.SCB.ZombieClass;
import mediaapps.SCB.interfaces.ClassInterface;

 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.Random;

 import org.bukkit.plugin.Plugin;
 
 public class ClassManager
 {
   Plugin plugin = SCB.getInstance();
   public static ClassManager cmanger = new ClassManager();
 
   HashMap<String, ClassInterface> classes = new HashMap();
   ArrayList<String> classes2 = new ArrayList();
   HashMap<String, ClassInterface> gemClasses = new HashMap();
   HashMap<String, Integer> gemCost = new HashMap();
   HashMap<String, ClassInterface> vipClasses = new HashMap();
 
   public static ClassManager get()
   {
     return cmanger;
   }
 
   public void loadClasses()
   {
     this.classes.put("creeper", new CreeperClass());
     this.classes.put("zombie", new ZombieClass());
     this.classes.put("blaze", new BlazeClass());
     this.classes.put("cactus", new CactusClass());
     this.classes.put("skeleton", new SkeletonClass());
     this.classes.put("spider", new SpiderClass());
     this.classes.put("enderman", new EndermanClass());
             this.classes.put("wither", new WitherClass());
 
     this.classes2.add("creeper");
     this.classes2.add("zombie");
     this.classes2.add("blaze");
     this.classes2.add("cactus");
     this.classes2.add("skeleton");
     this.classes2.add("spider");
     this.classes2.add("enderman");
             this.classes2.add("wither");
   }
 
   public ClassInterface getClasse(String cn) {
     return (ClassInterface)this.classes.get(cn);
   }
 
   public void addRegularClass(String Name, ClassInterface ci) {
     this.classes.put(Name, ci);
     this.classes2.add(Name);
   }
 
   public void addGemClass(String name, ClassInterface ci, int cost) {
     this.gemClasses.put(name, ci);
     this.gemCost.put(name, Integer.valueOf(cost));
   }
 
   public void addVipClass(String name, ClassInterface ci) {
     this.vipClasses.put(name, ci);
   }
 
   public ClassInterface[] getRegularClasses() {
     ArrayList ci = new ArrayList(this.classes.values());
     return (ClassInterface[])ci.toArray(new ClassInterface[ci.size()]);
   }
 
   public ClassInterface[] getGemClasses() {
     ArrayList ci = new ArrayList(this.gemClasses.values());
     return (ClassInterface[])ci.toArray(new ClassInterface[ci.size()]);
   }
 
   public ClassInterface[] getVipClasses() {
     ArrayList ci = new ArrayList(this.vipClasses.values());
     return (ClassInterface[])ci.toArray(new ClassInterface[ci.size()]);
   }
 
   public ClassInterface getRandomClass() {
     Random random = new Random();
     int num = random.nextInt(this.classes2.size() - 1) + 1;
     return (ClassInterface)this.classes.get(this.classes2.get(num));
   }
 }