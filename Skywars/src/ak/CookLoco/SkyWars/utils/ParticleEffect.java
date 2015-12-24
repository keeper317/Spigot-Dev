package ak.CookLoco.SkyWars.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public enum ParticleEffect
{
  EXPLOSION_NORMAL("explode", 0, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }),  EXPLOSION_LARGE("largeexplode", 1, -1, new ParticleProperty[0]),  EXPLOSION_HUGE("hugeexplosion", 2, -1, new ParticleProperty[0]),  FIREWORKS_SPARK("fireworksSpark", 3, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }),  WATER_BUBBLE("bubble", 4, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL, ParticleProperty.REQUIRES_WATER }),  WATER_SPLASH("splash", 5, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }),  WATER_WAKE("wake", 6, 7, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }),  SUSPENDED("suspended", 7, -1, new ParticleProperty[] { ParticleProperty.REQUIRES_WATER }),  SUSPENDED_DEPTH("depthSuspend", 8, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }),  CRIT("crit", 9, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }),  CRIT_MAGIC("magicCrit", 10, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }),  SMOKE_NORMAL("smoke", 11, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }),  SMOKE_LARGE("largesmoke", 12, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }),  SPELL("spell", 13, -1, new ParticleProperty[0]),  SPELL_INSTANT("instantSpell", 14, -1, new ParticleProperty[0]),  SPELL_MOB("mobSpell", 15, -1, new ParticleProperty[] { ParticleProperty.COLORABLE }),  SPELL_MOB_AMBIENT("mobSpellAmbient", 16, -1, new ParticleProperty[] { ParticleProperty.COLORABLE }),  SPELL_WITCH("witchMagic", 17, -1, new ParticleProperty[0]),  DRIP_WATER("dripWater", 18, -1, new ParticleProperty[0]),  DRIP_LAVA("dripLava", 19, -1, new ParticleProperty[0]),  VILLAGER_ANGRY("angryVillager", 20, -1, new ParticleProperty[0]),  VILLAGER_HAPPY("happyVillager", 21, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }),  TOWN_AURA("townaura", 22, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }),  NOTE("note", 23, -1, new ParticleProperty[] { ParticleProperty.COLORABLE }),  PORTAL("portal", 24, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }),  ENCHANTMENT_TABLE("enchantmenttable", 25, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }),  FLAME("flame", 26, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }),  FOOTSTEP("footstep", 28, -1, new ParticleProperty[0]),  CLOUD("cloud", 29, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }),  REDSTONE("reddust", 30, -1, new ParticleProperty[] { ParticleProperty.COLORABLE }),  SNOWBALL("snowballpoof", 31, -1, new ParticleProperty[0]),  SNOW_SHOVEL("snowshovel", 32, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL }),  SLIME("slime", 33, -1, new ParticleProperty[0]),  HEART("heart", 34, -1, new ParticleProperty[0]),  BARRIER("barrier", 35, 8, new ParticleProperty[0]),  ITEM_CRACK("iconcrack", 36, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL, ParticleProperty.REQUIRES_DATA }),  BLOCK_CRACK("blockcrack", 37, -1, new ParticleProperty[] { ParticleProperty.DIRECTIONAL, ParticleProperty.REQUIRES_DATA }),  BLOCK_DUST("blockdust", 38, 7, new ParticleProperty[] { ParticleProperty.DIRECTIONAL, ParticleProperty.REQUIRES_DATA }),  WATER_DROP("droplet", 39, 8, new ParticleProperty[0]),  ITEM_TAKE("take", 40, 8, new ParticleProperty[0]),  MOB_APPEARANCE("mobappearance", 41, 8, new ParticleProperty[0]);
  
  private static final Map<String, ParticleEffect> NAME_MAP;
  private static final Map<Integer, ParticleEffect> ID_MAP;
  private final String name;
  private final int id;
  private final int requiredVersion;
  private final List<ParticleProperty> properties;
  
  static
  {
    NAME_MAP = new HashMap();
    ID_MAP = new HashMap();
    ParticleEffect[] arrayOfParticleEffect;
    int j = (arrayOfParticleEffect = values()).length;
    for (int i = 0; i < j; i++)
    {
      ParticleEffect localParticleEffect = arrayOfParticleEffect[i];
      NAME_MAP.put(localParticleEffect.name, localParticleEffect);
      ID_MAP.put(Integer.valueOf(localParticleEffect.id), localParticleEffect);
    }
  }
  
  private ParticleEffect(String paramString1, int paramInt2, int paramInt3, ParticleProperty... paramVarArgs)
  {
    this.name = paramString1;
    this.id = paramInt2;
    this.requiredVersion = paramInt3;
    this.properties = Arrays.asList(paramVarArgs);
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public int getRequiredVersion()
  {
    return this.requiredVersion;
  }
  
  public boolean hasProperty(ParticleProperty paramParticleProperty)
  {
    return this.properties.contains(paramParticleProperty);
  }
  
  public boolean isSupported()
  {
    if (this.requiredVersion == -1) {
      return true;
    }
    return ParticlePacket.getVersion() >= this.requiredVersion;
  }
  
  public static ParticleEffect fromName(String paramString)
  {
    for (Map.Entry localEntry : NAME_MAP.entrySet()) {
      if (((String)localEntry.getKey()).equalsIgnoreCase(paramString)) {
        return (ParticleEffect)localEntry.getValue();
      }
    }
    return null;
  }
  
  public static ParticleEffect fromId(int paramInt)
  {
    for (Map.Entry localEntry : ID_MAP.entrySet()) {
      if (((Integer)localEntry.getKey()).intValue() == paramInt) {
        return (ParticleEffect)localEntry.getValue();
      }
    }
    return null;
  }
  
  private static boolean isWater(Location paramLocation)
  {
    Material localMaterial = paramLocation.getBlock().getType();
    return (localMaterial == Material.WATER) || (localMaterial == Material.STATIONARY_WATER);
  }
  
  private static boolean isLongDistance(Location paramLocation, List<Player> paramList)
  {
    String str = paramLocation.getWorld().getName();
    for (Player localPlayer : paramList)
    {
      Location localLocation = localPlayer.getLocation();
      if ((str.equals(localLocation.getWorld().getName())) && (localLocation.distanceSquared(paramLocation) >= 65536.0D)) {
        return true;
      }
    }
    return false;
  }
  
  private static boolean isDataCorrect(ParticleEffect paramParticleEffect, ParticleData paramParticleData)
  {
    return ((paramParticleEffect == BLOCK_CRACK) || (paramParticleEffect == BLOCK_DUST)) && (((paramParticleData instanceof BlockData)) || ((paramParticleEffect == ITEM_CRACK) && ((paramParticleData instanceof ItemData))));
  }
  
  private static boolean isColorCorrect(ParticleEffect paramParticleEffect, ParticleColor paramParticleColor)
  {
    return ((paramParticleEffect == SPELL_MOB) || (paramParticleEffect == SPELL_MOB_AMBIENT) || (paramParticleEffect == REDSTONE)) && (((paramParticleColor instanceof OrdinaryColor)) || ((paramParticleEffect == NOTE) && ((paramParticleColor instanceof NoteColor))));
  }
  
  public void display(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt, Location paramLocation, double paramDouble)
  {
    if (!isSupported()) {
      throw new ParticleVersionException("This particle effect is not supported by your server version");
    }
    if (hasProperty(ParticleProperty.REQUIRES_DATA)) {
      throw new ParticleDataException("This particle effect requires additional data");
    }
    if ((hasProperty(ParticleProperty.REQUIRES_WATER)) && (!isWater(paramLocation))) {
      throw new IllegalArgumentException("There is no water at the center location");
    }
    new ParticlePacket(this, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramInt, paramDouble > 256.0D, null).sendTo(paramLocation, paramDouble);
  }
  
  public void display(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt, Location paramLocation, List<Player> paramList)
  {
    if (!isSupported()) {
      throw new ParticleVersionException("This particle effect is not supported by your server version");
    }
    if (hasProperty(ParticleProperty.REQUIRES_DATA)) {
      throw new ParticleDataException("This particle effect requires additional data");
    }
    if ((hasProperty(ParticleProperty.REQUIRES_WATER)) && (!isWater(paramLocation))) {
      throw new IllegalArgumentException("There is no water at the center location");
    }
    new ParticlePacket(this, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramInt, isLongDistance(paramLocation, paramList), null).sendTo(paramLocation, paramList);
  }
  
  public void display(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt, Location paramLocation, Player... paramVarArgs)
  {
    display(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramInt, paramLocation, Arrays.asList(paramVarArgs));
  }
  
  public void display(Vector paramVector, float paramFloat, Location paramLocation, double paramDouble)
  {
    if (!isSupported()) {
      throw new ParticleVersionException("This particle effect is not supported by your server version");
    }
    if (hasProperty(ParticleProperty.REQUIRES_DATA)) {
      throw new ParticleDataException("This particle effect requires additional data");
    }
    if (!hasProperty(ParticleProperty.DIRECTIONAL)) {
      throw new IllegalArgumentException("This particle effect is not directional");
    }
    if ((hasProperty(ParticleProperty.REQUIRES_WATER)) && (!isWater(paramLocation))) {
      throw new IllegalArgumentException("There is no water at the center location");
    }
    new ParticlePacket(this, paramVector, paramFloat, paramDouble > 256.0D, null).sendTo(paramLocation, paramDouble);
  }
  
  public void display(Vector paramVector, float paramFloat, Location paramLocation, List<Player> paramList)
  {
    if (!isSupported()) {
      throw new ParticleVersionException("This particle effect is not supported by your server version");
    }
    if (hasProperty(ParticleProperty.REQUIRES_DATA)) {
      throw new ParticleDataException("This particle effect requires additional data");
    }
    if (!hasProperty(ParticleProperty.DIRECTIONAL)) {
      throw new IllegalArgumentException("This particle effect is not directional");
    }
    if ((hasProperty(ParticleProperty.REQUIRES_WATER)) && (!isWater(paramLocation))) {
      throw new IllegalArgumentException("There is no water at the center location");
    }
    new ParticlePacket(this, paramVector, paramFloat, isLongDistance(paramLocation, paramList), null).sendTo(paramLocation, paramList);
  }
  
  public void display(Vector paramVector, float paramFloat, Location paramLocation, Player... paramVarArgs)
  {
    display(paramVector, paramFloat, paramLocation, Arrays.asList(paramVarArgs));
  }
  
  public void display(ParticleColor paramParticleColor, Location paramLocation, double paramDouble)
  {
    if (!isSupported()) {
      throw new ParticleVersionException("This particle effect is not supported by your server version");
    }
    if (!hasProperty(ParticleProperty.COLORABLE)) {
      throw new ParticleColorException("This particle effect is not colorable");
    }
    if (!isColorCorrect(this, paramParticleColor)) {
      throw new ParticleColorException("The particle color type is incorrect");
    }
    new ParticlePacket(this, paramParticleColor, paramDouble > 256.0D).sendTo(paramLocation, paramDouble);
  }
  
  public void display(ParticleColor paramParticleColor, Location paramLocation, List<Player> paramList)
  {
    if (!isSupported()) {
      throw new ParticleVersionException("This particle effect is not supported by your server version");
    }
    if (!hasProperty(ParticleProperty.COLORABLE)) {
      throw new ParticleColorException("This particle effect is not colorable");
    }
    if (!isColorCorrect(this, paramParticleColor)) {
      throw new ParticleColorException("The particle color type is incorrect");
    }
    new ParticlePacket(this, paramParticleColor, isLongDistance(paramLocation, paramList)).sendTo(paramLocation, paramList);
  }
  
  public void display(ParticleColor paramParticleColor, Location paramLocation, Player... paramVarArgs)
  {
    display(paramParticleColor, paramLocation, Arrays.asList(paramVarArgs));
  }
  
  public void display(ParticleData paramParticleData, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt, Location paramLocation, double paramDouble)
  {
    if (!isSupported()) {
      throw new ParticleVersionException("This particle effect is not supported by your server version");
    }
    if (!hasProperty(ParticleProperty.REQUIRES_DATA)) {
      throw new ParticleDataException("This particle effect does not require additional data");
    }
    if (!isDataCorrect(this, paramParticleData)) {
      throw new ParticleDataException("The particle data type is incorrect");
    }
    new ParticlePacket(this, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramInt, paramDouble > 256.0D, paramParticleData).sendTo(paramLocation, paramDouble);
  }
  
  public void display(ParticleData paramParticleData, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt, Location paramLocation, List<Player> paramList)
  {
    if (!isSupported()) {
      throw new ParticleVersionException("This particle effect is not supported by your server version");
    }
    if (!hasProperty(ParticleProperty.REQUIRES_DATA)) {
      throw new ParticleDataException("This particle effect does not require additional data");
    }
    if (!isDataCorrect(this, paramParticleData)) {
      throw new ParticleDataException("The particle data type is incorrect");
    }
    new ParticlePacket(this, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramInt, isLongDistance(paramLocation, paramList), paramParticleData).sendTo(paramLocation, paramList);
  }
  
  public void display(ParticleData paramParticleData, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt, Location paramLocation, Player... paramVarArgs)
  {
    display(paramParticleData, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramInt, paramLocation, Arrays.asList(paramVarArgs));
  }
  
  public void display(ParticleData paramParticleData, Vector paramVector, float paramFloat, Location paramLocation, double paramDouble)
  {
    if (!isSupported()) {
      throw new ParticleVersionException("This particle effect is not supported by your server version");
    }
    if (!hasProperty(ParticleProperty.REQUIRES_DATA)) {
      throw new ParticleDataException("This particle effect does not require additional data");
    }
    if (!isDataCorrect(this, paramParticleData)) {
      throw new ParticleDataException("The particle data type is incorrect");
    }
    new ParticlePacket(this, paramVector, paramFloat, paramDouble > 256.0D, paramParticleData).sendTo(paramLocation, paramDouble);
  }
  
  public void display(ParticleData paramParticleData, Vector paramVector, float paramFloat, Location paramLocation, List<Player> paramList)
  {
    if (!isSupported()) {
      throw new ParticleVersionException("This particle effect is not supported by your server version");
    }
    if (!hasProperty(ParticleProperty.REQUIRES_DATA)) {
      throw new ParticleDataException("This particle effect does not require additional data");
    }
    if (!isDataCorrect(this, paramParticleData)) {
      throw new ParticleDataException("The particle data type is incorrect");
    }
    new ParticlePacket(this, paramVector, paramFloat, isLongDistance(paramLocation, paramList), paramParticleData).sendTo(paramLocation, paramList);
  }
  
  public void display(ParticleData paramParticleData, Vector paramVector, float paramFloat, Location paramLocation, Player... paramVarArgs)
  {
    display(paramParticleData, paramVector, paramFloat, paramLocation, Arrays.asList(paramVarArgs));
  }
  
  public static enum ParticleProperty
  {
    REQUIRES_WATER,  REQUIRES_DATA,  DIRECTIONAL,  COLORABLE;
  }
  
  public static abstract class ParticleData
  {
    private final Material material;
    private final byte data;
    private final int[] packetData;
    
    public ParticleData(Material paramMaterial, byte paramByte)
    {
      this.material = paramMaterial;
      this.data = paramByte;
      this.packetData = new int[] { paramMaterial.getId(), paramByte };
    }
    
    public Material getMaterial()
    {
      return this.material;
    }
    
    public byte getData()
    {
      return this.data;
    }
    
    public int[] getPacketData()
    {
      return this.packetData;
    }
    
    public String getPacketDataString()
    {
      return "_" + this.packetData[0] + "_" + this.packetData[1];
    }
  }
  
  public static final class ItemData
    extends ParticleEffect.ParticleData
  {
    public ItemData(Material paramMaterial, byte paramByte)
    {
      super(paramByte);
    }
  }
  
  public static final class BlockData
    extends ParticleEffect.ParticleData
  {
    public BlockData(Material paramMaterial, byte paramByte)
    {
      super(paramByte);
      if (!paramMaterial.isBlock()) {
        throw new IllegalArgumentException("The material is not a block");
      }
    }
  }
  
  public static final class OrdinaryColor
    extends ParticleEffect.ParticleColor
  {
    private final int red;
    private final int green;
    private final int blue;
    
    public OrdinaryColor(int paramInt1, int paramInt2, int paramInt3)
    {
      if (paramInt1 < 0) {
        throw new IllegalArgumentException("The red value is lower than 0");
      }
      if (paramInt1 > 255) {
        throw new IllegalArgumentException("The red value is higher than 255");
      }
      this.red = paramInt1;
      if (paramInt2 < 0) {
        throw new IllegalArgumentException("The green value is lower than 0");
      }
      if (paramInt2 > 255) {
        throw new IllegalArgumentException("The green value is higher than 255");
      }
      this.green = paramInt2;
      if (paramInt3 < 0) {
        throw new IllegalArgumentException("The blue value is lower than 0");
      }
      if (paramInt3 > 255) {
        throw new IllegalArgumentException("The blue value is higher than 255");
      }
      this.blue = paramInt3;
    }
    
    public int getRed()
    {
      return this.red;
    }
    
    public int getGreen()
    {
      return this.green;
    }
    
    public int getBlue()
    {
      return this.blue;
    }
    
    public float getValueX()
    {
      return this.red / 255.0F;
    }
    
    public float getValueY()
    {
      return this.green / 255.0F;
    }
    
    public float getValueZ()
    {
      return this.blue / 255.0F;
    }
  }
  
  public static final class NoteColor
    extends ParticleEffect.ParticleColor
  {
    private final int note;
    
    public NoteColor(int paramInt)
    {
      if (paramInt < 0) {
        throw new IllegalArgumentException("The note value is lower than 0");
      }
      if (paramInt > 24) {
        throw new IllegalArgumentException("The note value is higher than 24");
      }
      this.note = paramInt;
    }
    
    public float getValueX()
    {
      return this.note / 24.0F;
    }
    
    public float getValueY()
    {
      return 0.0F;
    }
    
    public float getValueZ()
    {
      return 0.0F;
    }
  }
  
  private static final class ParticleDataException
    extends RuntimeException
  {
    private static final long serialVersionUID = 3203085387160737484L;
    
    public ParticleDataException(String paramString)
    {
      super();
    }
  }
  
  private static final class ParticleColorException
    extends RuntimeException
  {
    private static final long serialVersionUID = 3203085387160737484L;
    
    public ParticleColorException(String paramString)
    {
      super();
    }
  }
  
  private static final class ParticleVersionException
    extends RuntimeException
  {
    private static final long serialVersionUID = 3203085387160737484L;
    
    public ParticleVersionException(String paramString)
    {
      super();
    }
  }
  
  public static final class ParticlePacket
  {
    private static int version;
    private static Class<?> enumParticle;
    private static Constructor<?> packetConstructor;
    private static Method getHandle;
    private static Field playerConnection;
    private static Method sendPacket;
    private static boolean initialized;
    private final ParticleEffect effect;
    private final float offsetX;
    private final float offsetY;
    private final float offsetZ;
    private final float speed;
    private final int amount;
    private final boolean longDistance;
    private final ParticleEffect.ParticleData data;
    private Object packet;
    
    public ParticlePacket(ParticleEffect paramParticleEffect, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt, boolean paramBoolean, ParticleEffect.ParticleData paramParticleData)
    {
      initialize();
      if (paramFloat4 < 0.0F) {
        throw new IllegalArgumentException("The speed is lower than 0");
      }
      if (paramInt < 0) {
        throw new IllegalArgumentException("The amount is lower than 0");
      }
      this.effect = paramParticleEffect;
      this.offsetX = paramFloat1;
      this.offsetY = paramFloat2;
      this.offsetZ = paramFloat3;
      this.speed = paramFloat4;
      this.amount = paramInt;
      this.longDistance = paramBoolean;
      this.data = paramParticleData;
    }
    
    public ParticlePacket(ParticleEffect paramParticleEffect, Vector paramVector, float paramFloat, boolean paramBoolean, ParticleEffect.ParticleData paramParticleData)
    {
      this(paramParticleEffect, (float)paramVector.getX(), (float)paramVector.getY(), (float)paramVector.getZ(), paramFloat, 0, paramBoolean, paramParticleData);
    }
    
    public ParticlePacket(ParticleEffect paramParticleEffect, ParticleEffect.ParticleColor paramParticleColor, boolean paramBoolean)
    {
      this(paramParticleEffect, paramParticleColor.getValueX(), paramParticleColor.getValueY(), paramParticleColor.getValueZ(), 1.0F, 0, paramBoolean, null);
    }
    
    public static void initialize()
    {
      if (initialized) {
        return;
      }
      try
      {
        version = Integer.parseInt(Character.toString(ReflectionUtils.PackageType.getServerVersion().charAt(3)));
        if (version > 7) {
          enumParticle = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("EnumParticle");
        }
        Class localClass = ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass(version < 7 ? "Packet63WorldParticles" : "PacketPlayOutWorldParticles");
        packetConstructor = ReflectionUtils.getConstructor(localClass, new Class[0]);
        getHandle = ReflectionUtils.getMethod("CraftPlayer", ReflectionUtils.PackageType.CRAFTBUKKIT_ENTITY, "getHandle", new Class[0]);
        playerConnection = ReflectionUtils.getField("EntityPlayer", ReflectionUtils.PackageType.MINECRAFT_SERVER, false, "playerConnection");
        sendPacket = ReflectionUtils.getMethod(playerConnection.getType(), "sendPacket", new Class[] { ReflectionUtils.PackageType.MINECRAFT_SERVER.getClass("Packet") });
      }
      catch (Exception localException)
      {
        throw new VersionIncompatibleException("Your current bukkit version seems to be incompatible with this library", localException);
      }
      initialized = true;
    }
    
    public static int getVersion()
    {
      if (!initialized) {
        initialize();
      }
      return version;
    }
    
    public static boolean isInitialized()
    {
      return initialized;
    }
    
    private void initializePacket(Location paramLocation)
    {
      if (this.packet != null) {
        return;
      }
      try
      {
        this.packet = packetConstructor.newInstance(new Object[0]);
        if (version < 8)
        {
          String str = this.effect.getName();
          if (this.data != null) {
            str = str + this.data.getPacketDataString();
          }
          ReflectionUtils.setValue(this.packet, true, "a", str);
        }
        else
        {
          ReflectionUtils.setValue(this.packet, true, "a", enumParticle.getEnumConstants()[this.effect.getId()]);
          ReflectionUtils.setValue(this.packet, true, "j", Boolean.valueOf(this.longDistance));
          if (this.data != null) {
            ReflectionUtils.setValue(this.packet, true, "k", this.data.getPacketData());
          }
        }
        ReflectionUtils.setValue(this.packet, true, "b", Float.valueOf((float)paramLocation.getX()));
        ReflectionUtils.setValue(this.packet, true, "c", Float.valueOf((float)paramLocation.getY()));
        ReflectionUtils.setValue(this.packet, true, "d", Float.valueOf((float)paramLocation.getZ()));
        ReflectionUtils.setValue(this.packet, true, "e", Float.valueOf(this.offsetX));
        ReflectionUtils.setValue(this.packet, true, "f", Float.valueOf(this.offsetY));
        ReflectionUtils.setValue(this.packet, true, "g", Float.valueOf(this.offsetZ));
        ReflectionUtils.setValue(this.packet, true, "h", Float.valueOf(this.speed));
        ReflectionUtils.setValue(this.packet, true, "i", Integer.valueOf(this.amount));
      }
      catch (Exception localException)
      {
        throw new PacketInstantiationException("Packet instantiation failed", localException);
      }
    }
    
    public void sendTo(Location paramLocation, Player paramPlayer)
    {
      initializePacket(paramLocation);
      try
      {
        sendPacket.invoke(playerConnection.get(getHandle.invoke(paramPlayer, new Object[0])), new Object[] { this.packet });
      }
      catch (Exception localException)
      {
        throw new PacketSendingException("Failed to send the packet to player '" + paramPlayer.getName() + "'", localException);
      }
    }
    
    public void sendTo(Location paramLocation, List<Player> paramList)
    {
      if (paramList.isEmpty()) {
        throw new IllegalArgumentException("The player list is empty");
      }
      for (Player localPlayer : paramList) {
        sendTo(paramLocation, localPlayer);
      }
    }
    
    public void sendTo(Location paramLocation, double paramDouble)
    {
      if (paramDouble < 1.0D) {
        throw new IllegalArgumentException("The range is lower than 1");
      }
      String str = paramLocation.getWorld().getName();
      double d = paramDouble * paramDouble;
      for (Player localPlayer : Bukkit.getOnlinePlayers()) {
        if ((localPlayer.getWorld().getName().equals(str)) && (localPlayer.getLocation().distanceSquared(paramLocation) <= d)) {
          sendTo(paramLocation, localPlayer);
        }
      }
    }
    
    private static final class VersionIncompatibleException
      extends RuntimeException
    {
      private static final long serialVersionUID = 3203085387160737484L;
      
      public VersionIncompatibleException(String paramString, Throwable paramThrowable)
      {
        super(paramThrowable);
      }
    }
    
    private static final class PacketInstantiationException
      extends RuntimeException
    {
      private static final long serialVersionUID = 3203085387160737484L;
      
      public PacketInstantiationException(String paramString, Throwable paramThrowable)
      {
        super(paramThrowable);
      }
    }
    
    private static final class PacketSendingException
      extends RuntimeException
    {
      private static final long serialVersionUID = 3203085387160737484L;
      
      public PacketSendingException(String paramString, Throwable paramThrowable)
      {
        super(paramThrowable);
      }
    }
  }
  
  public static abstract class ParticleColor
  {
    public abstract float getValueX();
    
    public abstract float getValueY();
    
    public abstract float getValueZ();
  }
}