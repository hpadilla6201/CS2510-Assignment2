import tester.Tester;

// represents a web page in the Internet
class WebPage {
  String title;
  String url;
  ILoItem items;

  // constructor
  WebPage(String title, String url, ILoItem items) {
    this.title = title;
    this.url = url;
    this.items = items;
  }

  /*
   * Fields: this.title ... String this.url ... String this.items ... ILoItem
   * 
   * Methods: totalImageSize() ... int textLength() ... int images() ... String
   * 
   * Methods for Fields: this.items.imageSize() ... int
   * this.items.textLengthHelp() ... int this.items.stringImages() ... String
   */

  // Size of Image in WebPage
  int totalImageSize() {
    return this.items.imageSize();

  }

  // number of letters in all text that appears on the web site
  int textLength() {
    return this.title.length() + this.items.textLengthHelp();
  }

  // produces one String that has in it all names of images
  // on this web page and all web pages linked to it
  String images() {
    return this.items.stringImages();
  }

}

// an item is one of Text, Image, or Link
interface IItem {

  // size of an image item
  int itemImageSize();

  // length of string for an item
  int itemLength();

  // string of images in item
  String itemString();

}

// list of IItems
interface ILoItem {

  // count the total size of all images in a web page
  int imageSize();

  // helper function for textLength
  int textLengthHelp();

  // gets the string of images in a list
  String stringImages();

}

// List of empty list of IItem
class MtLoItem implements ILoItem {
  MtLoItem() {
  }

  // count the total size of all images in a web page
  public int imageSize() {
    return 0;
  }

  // helper function for textLength
  public int textLengthHelp() {
    return 0;
  }

  // gets the string of images in a empty list
  public String stringImages() {
    return "";
  }

}

// cons class
class ConsLoItem implements ILoItem {

  IItem first;
  ILoItem rest;

  ConsLoItem(IItem first, ILoItem rest) {
    this.first = first;
    this.rest = rest;
  }
  /*
   * Fields: this.first ... IItem this.rest ... ILoItem
   * 
   * Methods: totalImageSize() ... int textLengthHelp() ... int StringImages() ...
   * String
   * 
   * 
   * Methods for Fields: this.first.itemImageSize() ... int
   * this.first.itemLength() ... int this.first.itemString() ... String
   * this.rest.imageSize() ... int this.rest.stringImages ... String
   * this.rest.textLengthHelp() ... int
   */

  // count the total size of all images in a web page
  public int imageSize() {
    return this.first.itemImageSize() + this.rest.imageSize();

  }

  // helper function for textLength
  public int textLengthHelp() {
    return this.first.itemLength() + this.rest.textLengthHelp();
  }

  // gets the string of images in a list
  public String stringImages() {
    if (this.first.itemString().equals("") || this.rest.stringImages().equals("")) {
      return this.first.itemString() + this.rest.stringImages();
    }
    else {
      return this.first.itemString() + ", " + this.rest.stringImages();
    }

  }
}

// represents Text 
class Text implements IItem {
  String contents;

  // constructor
  Text(String contents) {
    this.contents = contents;

  }
  /*
   * Fields: this.contents ... String
   * 
   * Methods: this.itemImageSize() ... int this.itemLength() ... int
   * this.itemString() ... String
   * 
   * 
   * Methods for Fields: none
   */

  // returns size of the image for text
  public int itemImageSize() {
    return 0;
  }

  // length of string for an item
  public int itemLength() {
    // TODO Auto-generated method stub
    return this.contents.length();
  }

  // string of images in item
  public String itemString() {
    // TODO Auto-generated method stub
    return "";
  }
}

// represents Image 
class Image implements IItem {
  String fileName;
  int size;
  String fileType;

  // constructor
  Image(String fileName, int size, String fileType) {
    this.fileName = fileName;
    this.size = size;
    this.fileType = fileType;
  }
  /*
   * Fields: this.fileName ... String this.size ... int this.fileType ... String
   * 
   * Methods: itemImageSize() ... int itemLength() ... int itemString ... String
   * 
   * Methods for Fields: none
   * 
   */

  // returns the size of image
  public int itemImageSize() {
    return this.size;

  }

  // length of string for an item
  public int itemLength() {
    // TODO Auto-generated method stub
    return this.fileName.length() + this.fileType.length();
  }

  // string of Images in item
  public String itemString() {
    // TODO Auto-generated method stub
    return this.fileName + "." + this.fileType;
  }
}

// represents a link
class Link implements IItem {
  String name;
  WebPage page;

  // constructor
  Link(String name, WebPage page) {
    this.name = name;
    this.page = page;
  }
  /*
   * Fields: this.name ... String this.page ... WebPage
   * 
   * Methods: itemImageSize() ... int itemLength() ... int itemLength() ... String
   * 
   * Methods for Fields: this.page.textLength() ... int this.page.totalImageSize()
   * ... int this.page.images() ... String this.page.items ... WebPage
   * this.page.title ... WebPage this.page.url ... WebPage
   * 
   */

  // returns size of the image for a link
  public int itemImageSize() {

    return this.page.totalImageSize();
  }

  // length of string for an item
  public int itemLength() {
    return this.name.length() + this.page.textLength();

  }

  // string of images in item
  public String itemString() {
    // TODO Auto-generated method stub
    return this.page.images();
  }

}

// examples of web pages
class ExamplesWebPage {
  ILoItem mt = new MtLoItem();

  Image professors = new Image("profs", 240, "jpeg");
  Text theStaff = new Text("The staff");
  Image wvhLab = new Image("wvh-lab", 400, "png");
  Text homeSweet = new Text("Home sweet home");
  Image cover = new Image("htdp", 4300, "tiff");
  Text htdp = new Text("How to Design Programs");
  Text sCJ = new Text("Stay classy, Java");

  ILoItem htdpList1 = new ConsLoItem(this.cover, this.mt);
  ILoItem htdpList2 = new ConsLoItem(this.htdp, this.htdpList1);

  WebPage htdpWP = new WebPage("HtDP", "htdp.org", this.htdpList2);
  Link backToTheFuture = new Link("Back to the Future", this.htdpWP);

  ILoItem oodList1 = new ConsLoItem(this.backToTheFuture, this.mt);
  ILoItem oodList2 = new ConsLoItem(this.sCJ, this.oodList1);

  WebPage oodWP = new WebPage("OOD", "ccs.neu.edu/OOD", this.oodList2);

  Link aLookAhead = new Link("A Look Ahead", this.oodWP);

  ILoItem fList1 = new ConsLoItem(this.aLookAhead, this.mt);

  Link aLookBack = new Link("A Look Back", this.htdpWP);

  ILoItem fList2 = new ConsLoItem(this.aLookBack, this.fList1);
  ILoItem fList3 = new ConsLoItem(this.professors, this.fList2);
  ILoItem fList4 = new ConsLoItem(this.theStaff, this.fList3);
  ILoItem fList5 = new ConsLoItem(this.wvhLab, this.fList4);
  ILoItem fList6 = new ConsLoItem(this.homeSweet, this.fList5);

  WebPage fundiesWP = new WebPage("Fundies II", "ccs.neu.edu/Fundies2", this.fList6);

  // the contents of a webpage that has at least one text, two images, three
  // pages, and four links.
  Text mystreet = new Text("West Cole");
  Image myImage1 = new Image("image", 500, "jpeg");
  Image myImage2 = new Image("image2", 1000, "png");

  Link myLink1 = new Link("Link1", this.myWP1);
  Link myLink2 = new Link("Link2", this.myWP1);
  Link myLink3 = new Link("Link3", this.myWP2);
  Link myLink4 = new Link("Link4", this.myWP3);

  ILoItem myList1 = new ConsLoItem(this.mystreet, this.mt);
  ILoItem myList2 = new ConsLoItem(this.myImage1, this.myList1);
  ILoItem myList3 = new ConsLoItem(this.myImage2, this.myList2);

  WebPage myWP1 = new WebPage("Hector", "hector.com", this.myList1);
  WebPage myWP2 = new WebPage("Gianna", "gianna.com", this.myList2);
  WebPage myWP3 = new WebPage("Carlos", "carlos.com", this.myList3);

  // tests for totalImageSize
  boolean testTotalImageSize(Tester t) {
    return t.checkExpect(this.fundiesWP.totalImageSize(), 9240)
        && t.checkExpect(this.oodWP.totalImageSize(), 4300)
        && t.checkExpect(this.htdpWP.totalImageSize(), 4300)
        && t.checkExpect(this.myWP1.totalImageSize(), 0);

  }

  // tests for textLength
  boolean testTextLength(Tester t) {
    return t.checkExpect(this.fundiesWP.textLength(), 182)
        && t.checkExpect(this.oodWP.textLength(), 72) && t.checkExpect(this.htdpWP.textLength(), 34)
        && t.checkExpect(this.myWP1.textLength(), 15);
  }

  // tests for Images
  boolean testImages(Tester t) {
    return t.checkExpect(this.fundiesWP.images(), "wvh-lab.png, profs.jpeg, htdp.tiff, htdp.tiff")
        && t.checkExpect(this.oodWP.images(), "htdp.tiff")
        && t.checkExpect(this.htdpWP.images(), "htdp.tiff")
        && t.checkExpect(this.myWP1.images(), "");
  }

  // tests for imageSize
  boolean testImageSize(Tester t) {
    return t.checkExpect(this.htdpList1.imageSize(), 4300)
        && t.checkExpect(this.fList6.imageSize(), 9240);

  }

  // tests for textLengthHelp
  boolean testtextLengthHelp(Tester t) {
    return t.checkExpect(this.oodList1.textLengthHelp(), 52)
        && t.checkExpect(this.htdpList1.textLengthHelp(), 8)
        && t.checkExpect(this.fList6.textLengthHelp(), 172);
  }

  // tests for stringImages
  boolean teststringImages(Tester t) {
    return t.checkExpect(this.htdpList1.stringImages(), "htdp.tiff")
        && t.checkExpect(this.htdpList2.stringImages(), "htdp.tiff")
        && t.checkExpect(this.oodList1.stringImages(), "htdp.tiff")
        && t.checkExpect(this.oodList2.stringImages(), "htdp.tiff")
        && t.checkExpect(this.fList1.stringImages(), "htdp.tiff")
        && t.checkExpect(this.fList2.stringImages(), "htdp.tiff, htdp.tiff")
        && t.checkExpect(this.fList3.stringImages(), "profs.jpeg, htdp.tiff, htdp.tiff");

  }

  // tests for itemImageSize
  boolean testitemImageSize(Tester t) {
    return t.checkExpect(this.professors.itemImageSize(), 240)
        && t.checkExpect(this.theStaff.itemImageSize(), 0)
        && t.checkExpect(this.wvhLab.itemImageSize(), 400);

  }

  // tests for itemLength
  boolean testitemLength(Tester t) {
    return t.checkExpect(this.professors.itemLength(), 9)
        && t.checkExpect(this.theStaff.itemLength(), 9)
        && t.checkExpect(this.wvhLab.itemLength(), 10);
  }

  // test for itemString
  boolean testitemString(Tester t) {
    return t.checkExpect(this.professors.itemString(), "profs.jpeg")
        && t.checkExpect(this.theStaff.itemString(), "")
        && t.checkExpect(this.wvhLab.itemString(), "wvh-lab.png");
  }
  // why htdp.tiff appears twice in the results of the images method?
  /*
   * FundiesWP has alookBack in its list. A lookBack links to the web page htdpWP.
   * htdpWP contains cover in its list which has htdp.tiff.
   * 
   * FundiesWp also contains aLookAhead in its list. aLookAhead links to oodWP web
   * page. oodWP contains the link backToTheFuture which links to htdpWP. We know
   * htdpWP contains htdp.tiff, so that's why htdp.tiff appears twice. It's
   * because in its list it contains links that link to other web pages containing
   * htdp.tiff.
   * 
   */

  // tell us if there are any other places in your code where this duplication
  // occurs
  /*
   * htdpWP appears twice because fundies web page has two links to that web page.
   * 
   */

}
