/*
 * Copyright (C) 2007 J�lio Vilmar Gesser.
 * 
 * This file is part of Java 1.5 parser and Abstract Syntax Tree.
 *
 * Java 1.5 parser and Abstract Syntax Tree is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Java 1.5 parser and Abstract Syntax Tree is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Java 1.5 parser and Abstract Syntax Tree.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * Created on 05/10/2006
 */
package japa.parser.ast.type;

import japa.parser.ast.visitor.GenericVisitor;
import japa.parser.ast.visitor.VoidVisitor;
import symboltable.BuiltInTypeSymbol;
import symboltable.VariableSymbol;

/**
 * @author Julio Vilmar Gesser
 */
public final class PrimitiveType extends Type {

    public enum Primitive {
        Boolean, Char, Byte, Short, Int, Long, Float, Double
    }

    private final Primitive type;

    public PrimitiveType(int line, int column, Primitive type) {
        super(line, column);
        this.type = type;
    }

    public Primitive getType() {
        return type;
    }
    
    @Override
    public symboltable.Type castType()
    {
    	BuiltInTypeSymbol t = null;
    	switch(type){
    	case Boolean:
    		t = new BuiltInTypeSymbol("boolean");
    		break;
    	case Char:
    		t = new BuiltInTypeSymbol("char");
    		break;
    	case Byte:
    		t = new BuiltInTypeSymbol("byte");
    		break;
    	case Short:
    		t = new BuiltInTypeSymbol("short");
    		break;
    	case Int:
    		t = new BuiltInTypeSymbol("int");
    		break;
    	case Long:
    		t = new BuiltInTypeSymbol("long");
    		break;
    	case Float:
    		t = new BuiltInTypeSymbol("float");
    		break;
    	case Double:
    		t = new BuiltInTypeSymbol("double");
    		break;
    	}
    	t.setDefinedLine(this.getBeginLine());
		return t;
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

}
