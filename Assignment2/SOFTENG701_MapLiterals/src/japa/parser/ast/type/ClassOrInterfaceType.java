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
import symboltable.ClassSymbol;

import java.util.List;

/**
 * @author Julio Vilmar Gesser
 */
public final class ClassOrInterfaceType extends Type {

    private final ClassOrInterfaceType scope;

    private final String name;

    private List<Type> typeArgs;

    public ClassOrInterfaceType(int line, int column, ClassOrInterfaceType scope, String name, List<Type> typeArgs) {
        super(line, column);
        this.scope = scope;
        this.name = name;
        this.typeArgs = typeArgs;
    }

    public ClassOrInterfaceType getScope() {
        return scope;
    }

    public String getName() {
        return name;
    }

    public List<Type> getTypeArgs() {
        return typeArgs;
    }
    
    public void setTypeArgs(List<Type> typeArgs){
    	this.typeArgs = typeArgs;
    }
    
    @Override
    public symboltable.Type castType()
    {
    	ClassSymbol t = new ClassSymbol(name);
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
