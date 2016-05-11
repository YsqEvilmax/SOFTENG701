package symboltable;

import java.util.List;

public interface Scope {
	public String getScopeName();
	public Scope getEnclosingScope();
	public void setEnclosingScope(Scope scope);
	public void define(Symbol symbol);
	public Symbol resolve(String name);
	public Type resolveType(String name);
	public ScopedSymbol find();
	public boolean isLocal(Symbol s);
	public boolean isLocal(String s);
	public List<Symbol> getAllSymbols();
	public void printScopeHierarchy();
}
