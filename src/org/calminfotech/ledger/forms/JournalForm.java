package org.calminfotech.ledger.forms;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.calminfotech.ledger.models.JournalEntry;
import org.calminfotech.ledger.models.JournalHeader;

public class JournalForm {
	private JournalHeader journalHeader;
	private List<JournalEntry> journalEntries = new List<JournalEntry>() {

		@Override
		public boolean add(JournalEntry e) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void add(int index, JournalEntry element) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean addAll(Collection<? extends JournalEntry> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean addAll(int index, Collection<? extends JournalEntry> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean contains(Object o) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public JournalEntry get(int index) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int indexOf(Object o) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Iterator<JournalEntry> iterator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int lastIndexOf(Object o) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public ListIterator<JournalEntry> listIterator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ListIterator<JournalEntry> listIterator(int index) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean remove(Object o) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public JournalEntry remove(int index) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public JournalEntry set(int index, JournalEntry element) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public List<JournalEntry> subList(int fromIndex, int toIndex) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object[] toArray() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T> T[] toArray(T[] a) {
			// TODO Auto-generated method stub
			return null;
		}
	};
	 
    
 
    public void addJournalEntry(JournalEntry journalEntry) {
    	System.out.println(journalEntry.getAmount());
        this.journalEntries.add(journalEntry);
    }

	public List<JournalEntry> getJournalEntries() {
		return journalEntries;
	}

	public void setJournalEntries(List<JournalEntry> journalEntries) {
		this.journalEntries = journalEntries;
	}

	public JournalHeader getJournalHeader() {
		return journalHeader;
	}

	public void setJournalHeader(JournalHeader journalHeader) {
		this.journalHeader = journalHeader;
	}
	

	
}
