package ch.unisg.sat.core.application;

import ch.unisg.sat.core.domain.entities.classes.ad.Issue;
import ch.unisg.sat.core.port.in.IssueUseCase;
import ch.unisg.sat.core.port.in.command.classes.IssueCommand;
import ch.unisg.sat.core.port.in.query.classes.IssueQuery;
import ch.unisg.sat.core.port.out.IssuePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IssueService implements IssueUseCase {

    private final IssuePort issuePort;

    @Override
    public void createIssue(IssueCommand command) {

        Issue issue = Issue.create(command.id(), command.title());

        issuePort.createIssue(issue);
    }

    @Override
    public Issue getIssueById(IssueQuery query) {

        Issue issue = Issue.create(query.id());

        return issuePort.getIssueById(issue);
    }
}
