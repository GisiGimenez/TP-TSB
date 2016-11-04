package Logica;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author julieta
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class Cola <E extends Comparable> implements Queue{
    
      private Node <E> frente;      
      private int cantidad;
      
      public Cola ()
      {
          frente = null;
          cantidad = 0;
      }

       public boolean addLast(E x)
      {
          boolean ban=true;
          
          if ( x  == null ) 
          {
              ban=false;
              return ban;
          }
            
          Node < E > nuevo = new Node <>( x, null );
          Node < E > p = frente, q = null;
          while ( p != null )
          {
              q = p;
              p = p.getNext();
          }
          if( q != null ) q.setNext( nuevo );
          else frente = nuevo;
            
          cantidad++;
          return ban;
      } 
       
      public E element ()
      {
          if(frente==null) throw new NoSuchElementException("Error: la lista esta vacia...");
          E x= (E)frente.getInfo();
          
          return x;
      }
      
      public E peek()
      {
          if(frente==null) return null;
          E x= (E)frente.getInfo();
          
          return x;
      }
      public E poll()
      {
         if (frente == null) return null;
         
         E x = (E)frente.getInfo();
         frente = frente.getNext();
         
         cantidad--;
         return x;
      }
      
      public E remove()
      {
          if (frente == null) throw new NoSuchElementException("Error: la lista esta vacia...");
         
         E x = (E)frente.getInfo();
         frente = frente.getNext();
         
         cantidad--;
         return x;
      }
      public E[] toArray(){
          Node p=frente;
         
          ArrayList <E>vec = new ArrayList<E>();
          
          while(p!=null)
          {
              vec.add((E) p.getInfo());
              p=p.getNext();
            
          }
          E[]vector=(E[])vec.toArray();
          return vector;
      }
      
      public String toString()
      {
             Node <E> p = frente;
             String res = "[ ";
             while( p != null )
             {
                res = res + p.toString();
                if ( p.getNext() != null ) res = res + " - ";
                p = p.getNext();
             }
             res = res + " ]";
             return res;
      }

    @Override
    public boolean add(Object e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean offer(Object e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        return frente==null;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection c) {
        
        ArrayList <E> lista= (ArrayList<E>) c;
        for (int i = 0; i < lista.size(); i++) {
            this.addLast(lista.get(i));
        }
       return true;
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
