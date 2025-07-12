public class Element<T> {
  private Register<T> data;
  private boolean deleted;

  public Element() {
    this.data = null;
    this.deleted = false;
  }

  public Element(Register<T> data) {
    this.data = data;
    this.deleted = false;
  }

  public Register<T> getData() {
    return data;
  }

  public void setData(Register<T> data) {
    this.data = data;
    this.deleted = false;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public boolean isEmpty() {
    return data == null;
  }

  public boolean isAvailable() {
    return isEmpty() || deleted;
  }

  @Override
  public String toString() {
    if (isEmpty()) {
      return "[ EMPTY ]";
    } else if (deleted) {
      return "[ DELETED ]";
    } else {
      return "[ " + data.toString() + " ]";
    }
  }
}
