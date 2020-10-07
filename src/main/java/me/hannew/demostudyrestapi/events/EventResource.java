package me.hannew.demostudyrestapi.events;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class EventResource extends EntityModel<Event> {
    private Event event;

    public EventResource(Event content, Link... links){
        super(content, links);
        add(linkTo(EventController.class).slash(event.getId()).withSelfRel());
    }
}