package com.desafio.dbc.enums;

import java.io.Serializable;

public interface EnumType<T extends Serializable> {
	T getId();
}
