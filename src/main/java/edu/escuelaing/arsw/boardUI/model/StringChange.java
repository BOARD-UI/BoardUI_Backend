/*
 * Copyright (C) 2016 Pivotal Software, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.escuelaing.arsw.boardUI.model;

/**
 *
 * @author J382
 */
public class StringChange {
    Position position;
    String data;
    String type;

    public StringChange(){}

    public StringChange(String type, String data, Position position){
        this.data = data;
        this.type = type;
        this.position = position;
    }
    
    public String getData() {
        return data;
    }

    public Position getPosition() {
        return position;
    }

    public String getType() {
        return type;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("StringChange { type: '%s', data: '%s', position: %s }", type, data, position.toString());
    }
    
}
